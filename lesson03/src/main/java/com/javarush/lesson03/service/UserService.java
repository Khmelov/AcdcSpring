package com.javarush.lesson03.service;

import com.javarush.lesson03.entity.User;
import com.javarush.lesson03.processor.Watch;
import com.javarush.lesson03.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@ToString
@Watch
public class UserService {

    private final UserRepo userRepo;


    public User get(Long id) {
        ///logic

        return userRepo.getById(id).orElseThrow();
    }

}
