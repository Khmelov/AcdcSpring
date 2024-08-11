package com.javarush.lesson14.controller.rest;

import com.javarush.lesson14.dto.UserTo;
import com.javarush.lesson14.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(UserRestController.ENDPOINT_USERS_V1)
public class UserRestController {

    public static final String ENDPOINT_USERS_V1 = "/restapi/v1/users";
    private final UserService userService;

    //READ
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<UserTo> findAll() {
        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(
            value = "/{id}"
    )
    public Mono<UserTo> get(@PathVariable(name = "id") Long id) {
        return userService
                .get(id);
    }

    //WRITING
    //CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserTo> create(
            @RequestBody UserTo userTo
    ) {
        return userService.save(userTo);
    }

    //UPDATE
    @PutMapping("/{id}") //or @PatchMapping (if only part data update)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<UserTo> update(
            @RequestBody UserTo userTo
    ) {
        return userService.save(userTo);
    }

    //DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable(name = "id") Long id) {
        return userService.deleteById(id);
    }

}
