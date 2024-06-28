package com.javarush.lesson03.config;


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
            @Value("jakarta.persistence.jdbc.url") String url,
            @Value("jakarta.persistence.jdbc.user") String user,
            @Value("jakarta.persistence.jdbc.password") String password
    ) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
}
