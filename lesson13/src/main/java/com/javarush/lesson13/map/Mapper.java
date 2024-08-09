package com.javarush.lesson13.map;

import com.javarush.lesson13.dto.UserTo;
import com.javarush.lesson13.entity.User;


public class Mapper {

    private Mapper() {
    }

    public static User map(UserTo userTo) {
        return User.builder()
                .id(userTo.getId())
                .login(userTo.getLogin())
                .password(userTo.getPassword())
                .role(userTo.getRole())
                .build();
    }

    public static UserTo map(User user) {
        return UserTo.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
