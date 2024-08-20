package com.javarush.lesson17;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppWithSecurity {
    public static void main(String[] args) {
        var context = SpringApplication.run(AppWithSecurity.class, args);
        var beanFactory = context.getBeanFactory();
        System.out.println("debug");
    }
}
