package com.javarush.lesson03.config;


import com.javarush.lesson03.repo.UserRepo;
import com.javarush.lesson03.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties") //for read with @Value("${prop.key.name}")
public class Config {

    @Bean
    public ApplicationProperties applicationProperties(
            @Value("application.properties") String fileProperties
    ) {
        return new ApplicationProperties(fileProperties);
    }


}
