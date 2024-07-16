package com.javarush.lesson07.service;

import com.javarush.lesson07.entity.User;
import com.javarush.lesson07.repo.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void setPass(String password, Long id){
        userRepo.updatePassword(id,password);
    }

    @Transactional
    public User get(Long id) {
        User user = userRepo.findById(id).orElseThrow();
        return user;
    }

    @Transactional
    public List<User> findByNameAndRole(String loginPart,String rolePart){
        return userRepo.findUserByWith(loginPart,rolePart);
    }

    @PostConstruct
    void init() {
        System.out.println("UserRepo init");
    }

    public void show(int size){
        userRepo.showUsersByPage(size);
    }



}
