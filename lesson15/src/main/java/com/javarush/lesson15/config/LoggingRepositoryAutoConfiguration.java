package com.javarush.lesson15.config;

import com.javarush.lesson15.logger.LocatorAspect;
import com.javarush.lesson15.logger.LoggerAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnClass(LoggingRepositoryProperties.class)
@ConditionalOnProperty(
        prefix = LoggingRepositoryProperties.PREFIX,
        name = "state",
        havingValue = "ON",
        matchIfMissing = true
)
//вот это я и забыл добавить
@EnableConfigurationProperties(LoggingRepositoryProperties.class)
public class LoggingRepositoryAutoConfiguration {

    @Bean
    LocatorAspect locatorAspect() {
        return new LocatorAspect();
    }

    @Bean
    LoggerAspect loggerAspect() {
        return new LoggerAspect();
    }
}
