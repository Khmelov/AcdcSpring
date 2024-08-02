package com.javarush.lesson12.controller.http;

import com.javarush.lesson12.dto.UserTo;
import com.javarush.lesson12.entity.Role;
import com.javarush.lesson12.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@SessionAttributes({"currentUser"})
public class UserController {

    private final UserService userService;

    @ExceptionHandler(BindException.class)
    public String handleException(
            Model model,
            BindingResult bindingResult,
            BindException e
    ) {
        log.error("handle exception",e);
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        UserTo target = (UserTo)bindingResult.getTarget();
        model.addAttribute("user", target);
        model.addAttribute("errors", fieldErrors);
        model.addAttribute("roles", Role.values());
        return "userpage";
    }

    @GetMapping()
    public ModelAndView showAllUsers(ModelAndView view) {
        view.addObject("users", userService.findAll());
        view.setViewName("userpage");
        view.addObject("roles", Role.values());
        return view;
    }


    @GetMapping("/{id}")
    public ModelAndView showOneUserAndUsers(ModelAndView view, @PathVariable("id") Long id) {
        Optional<UserTo> optionalUser = userService.get(id);
        if (optionalUser.isPresent()) {
            view.addObject("user", optionalUser.get());
            view.addObject("users", userService.findAll());
        }
        view.addObject("roles", Role.values());
        view.setViewName("userpage");
        return view;
    }

    @PostMapping()
    public String processNewUserOrLogin(
            @Valid UserTo userTo,
            HttpSession session,
            @RequestParam(required = false, name = "createUser") String createUser
    ) {
        if (Objects.nonNull(createUser)) {
            userTo = userService.save(userTo);
            return "redirect:/users/%d".formatted(userTo.getId());
        } else {
            log.info(" userTo {} login ", userTo);
            session.setAttribute("currentUser", userTo);
            return "redirect:/users";
        }
    }


    @PostMapping("/{id}")
    public String updateOrDeleteUser(
            @Valid UserTo userTo,
            @RequestParam(required = false, name = "deleteUser") String deleteUser
    ) {
        if (Objects.nonNull(deleteUser)) {
            userService.delete(userTo);
            return "redirect:/users";
        } else {
            userService.save(userTo);
            return "redirect:/users/%d".formatted(userTo.getId());
        }
    }
}
