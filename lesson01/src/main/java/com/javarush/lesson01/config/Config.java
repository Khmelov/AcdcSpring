package com.javarush.lesson01.config;


import com.javarush.lesson01.repo.UserRepo;
import com.javarush.lesson01.service.UserService;
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

    @Bean
    public SessionCreator sessionCreator(ApplicationProperties applicationProperties) {
        return new SessionCreator(applicationProperties);
    }

    @Bean
    public UserRepo userRepo(SessionCreator sessionCreator) {
        return new UserRepo(sessionCreator);
    }

    @Bean
    public UserService userService(UserRepo userRepo) {
        return new UserService(userRepo);
    }


}
