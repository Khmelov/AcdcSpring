package com.javarush.lesson11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ApplicationUnderTests {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationUnderTests.class, args);
    }
}
