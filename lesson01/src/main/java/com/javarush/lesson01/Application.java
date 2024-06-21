package com.javarush.lesson01;

import com.javarush.lesson01.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author <a href=https://bit.ly/akhmelov>Alexander Khmelov</a>
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UserService userService = context.getBean(UserService.class);
        System.out.println(userService);
    }
}
