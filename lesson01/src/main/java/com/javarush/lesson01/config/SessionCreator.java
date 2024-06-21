package com.javarush.lesson01.config;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SessionCreator {

    private final SessionFactory sessionFactory;


    @SneakyThrows
    public SessionCreator(ApplicationProperties applicationProperties) {
        Configuration configuration = new Configuration();        //1. hibernate.properties
        configuration.addProperties(applicationProperties); //use 3.3 - my ApplicationProperties
        sessionFactory = configuration.buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();

    }


    public void close() {
        sessionFactory.close();
    }

}
