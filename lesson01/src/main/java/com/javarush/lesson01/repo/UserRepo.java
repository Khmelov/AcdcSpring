package com.javarush.lesson01.repo;


import com.javarush.lesson01.config.SessionCreator;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class UserRepo {

    private final SessionCreator sessionCreator;

}
