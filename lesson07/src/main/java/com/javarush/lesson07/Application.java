package com.javarush.lesson07;

import com.javarush.lesson07.entity.User;
import com.javarush.lesson07.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        UserService userService = context.getBean(UserService.class);
        List<User> users = userService.findByNameAndRole("o", "U");
        for (User user : users) {
            System.out.println(user);
        }
        userService.show(1);

    }
}
