package com.javarush.lesson05.service;

import com.javarush.lesson05.entity.User;
import com.javarush.lesson05.processor.Tx;
import com.javarush.lesson05.processor.Watch;
import com.javarush.lesson05.repo.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ToString
@Watch
@NoArgsConstructor
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService( UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Tx
    public User get(Long id) {
        ///logic

        return userRepo.getById(id).orElseThrow();
    }

    @PostConstruct
    void init(){
        System.out.println("UserRepo init");
    }

}
