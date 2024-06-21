package com.javarush.lesson01.service;

import com.javarush.lesson01.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@ToString
public class UserService {

    private final UserRepo userRepo;


}
