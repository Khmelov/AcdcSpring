package com.javarush.lesson13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AppWebFluxDemo {
    public static void main(String[] args) {
        SpringApplication.run(AppWebFluxDemo.class, args);
    }
}
