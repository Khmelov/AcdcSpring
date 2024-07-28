package com.javarush.lesson03.service;

import com.javarush.lesson03.entity.User;
import com.javarush.lesson03.processor.AutoInject;
import com.javarush.lesson03.processor.Tx;
import com.javarush.lesson03.processor.Watch;
import com.javarush.lesson03.repo.UserRepo;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ToString
public class UserService {


    //@Autowired
    private UserRepo userRepo;

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(@Autowired UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public UserService(@Autowired UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Tx
    public User get(Long id) {
        return userRepo.getById(id).orElseThrow();
    }

}
