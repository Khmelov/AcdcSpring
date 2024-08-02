package com.javarush.lesson12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AppWithValidDto {
    public static void main(String[] args) {
        SpringApplication.run(AppWithValidDto.class, args);
    }
}
