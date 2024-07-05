package com.javarush.lesson05.config;


import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
public class DataConnection {

    final String url;
    final String user;
    final String password;

    public DataConnection(
            @Value("#{T(java.lang.Math).random()}") String url,
            @Value("${jakarta.persistence.jdbc.user}") String user,
            @Value("${jakarta.persistence.jdbc.password}") String password
    ) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @PostConstruct
    void showState(){
        System.out.println("!!!!"+this);
    }
}
