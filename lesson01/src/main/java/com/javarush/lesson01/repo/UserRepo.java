package com.javarush.lesson01.repo;


import com.javarush.lesson01.config.SessionCreator;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
@ToString
public class UserRepo {

    private final SessionCreator sessionCreator;

}
