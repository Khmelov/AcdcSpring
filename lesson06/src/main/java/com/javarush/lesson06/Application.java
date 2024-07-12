package com.javarush.lesson06;

import com.javarush.lesson06.entity.User;
import com.javarush.lesson06.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        UserService userService = context.getBean(UserService.class);
        System.out.println(userService);
        User carl = userService.get(1L);
        System.out.println(carl);
    }
}
