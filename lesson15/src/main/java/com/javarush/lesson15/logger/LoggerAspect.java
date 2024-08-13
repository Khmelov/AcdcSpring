package com.javarush.lesson15.logger;

import com.javarush.lesson15.config.LoggingRepositoryProperties;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.event.Level;
import org.slf4j.spi.LoggingEventBuilder;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Aspect
public class LoggerAspect {

    @Autowired
    LoggingRepositoryProperties loggingRepositoryProperties;

    private LoggingEventBuilder out;

    @Around("com.javarush.lesson15.logger.LocatorAspect.isRepository()")
    public Object before(ProceedingJoinPoint proceedingJoinPoint) {
        String methodName = proceedingJoinPoint.toShortString();
        try {
            out.log("Before method: {}", methodName);
            Object result = proceedingJoinPoint.proceed();
            out.log("After method: {}", methodName);
            return result;
        } catch (Throwable e) {
            out.log("Error method: {} error: {}", methodName, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    void init() {
        String level = loggingRepositoryProperties.getLevel();
        out = log.makeLoggingEventBuilder(Level.valueOf(level));
    }


}
