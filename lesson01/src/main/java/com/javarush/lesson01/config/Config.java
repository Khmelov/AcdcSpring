package com.javarush.lesson01.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


public class Config {

    @Bean
    public ApplicationProperties applicationProperties(
            @Value("application.properties") String fileProperties
    ) {
        return new ApplicationProperties(fileProperties);
    }


}
