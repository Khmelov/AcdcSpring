package com.javarush.lesson03;

import com.javarush.lesson03.entity.User;
import com.javarush.lesson03.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author <a href=https://bit.ly/akhmelov>Alexander Khmelov</a>
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.javarush.lesson03");
        UserService userService = context.getBean(UserService.class);
        System.out.println(userService);
        User carl = userService.get(1L);
        System.out.println(carl);
    }
}
