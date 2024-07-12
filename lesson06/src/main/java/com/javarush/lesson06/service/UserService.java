package com.javarush.lesson06.service;

import com.javarush.lesson06.entity.User;
import com.javarush.lesson06.repo.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@ToString
@NoArgsConstructor
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    public User get(Long id) {
        User user = userRepo.findById(id).orElseThrow();
        return user;
    }

    @PostConstruct
    void init() {
        System.out.println("UserRepo init");
    }

}
