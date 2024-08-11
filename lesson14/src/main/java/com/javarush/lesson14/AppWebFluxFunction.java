package com.javarush.lesson14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AppWebFluxFunction {
    public static void main(String[] args) {
        SpringApplication.run(AppWebFluxFunction.class, args);
    }
}
