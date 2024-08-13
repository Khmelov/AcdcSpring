package com.javarush.lesson15.logger;

import com.javarush.lesson15.config.LoggingRepositoryProperties;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@EnableAspectJAutoProxy
public class LocatorAspect {

    @Pointcut("isRepositoryInterface()||isRepositoryPackage()")
    public void isRepository() {
    }

    @Pointcut("within(com..repository.impl)")
    public void isRepositoryPackage() {
    }

    @Pointcut("this(org.springframework.data.repository.Repository) ||" +
              "this(org.springframework.data.r2dbc.repository.R2dbcRepository)")
    public void isRepositoryInterface() {
    }

    @PostConstruct
    void initialize() {
        log.info("======= Initializing LocatorAspect =======");
    }

}
