package com.javarush.lesson18.map;

import com.javarush.lesson18.dto.UserTo;
import com.javarush.lesson18.entity.User;


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
                .password("")
                .role(user.getRole())
                .build();
    }
}
