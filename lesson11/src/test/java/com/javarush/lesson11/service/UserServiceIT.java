package com.javarush.lesson11.service;

import com.javarush.lesson11.annotation.MyContext;
import com.javarush.lesson11.entity.Role;
import com.javarush.lesson11.entity.User;
import com.javarush.lesson11.repository.Repo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestConstructor;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MyContext
@RequiredArgsConstructor
class UserServiceIT {

    public static final long USER_ID = 1L;

    private final UserService userService;

    final User carlInDb = User.builder()
            .id(1L)
            .login("Carl")
            .password("123")
            .role(Role.ADMIN)
            .build();

    final User alisaInDb = User.builder()
            .id(2L)
            .login("Alisa")
            .password("789")
            .role(Role.USER)
            .build();

    final User bobInDb = User.builder()
            .id(123L)
            .login("Bob")
            .role(Role.GUEST)
            .password("456")
            .build();

    final User newBob = User.builder()
            .login("Bob")
            .password("456")
            .role(Role.GUEST)
            .build();
    @Autowired
    private Repo repo;


    @Test
    void get() {
        //when
        Optional<User> user = userService.get(USER_ID);
        //then
        assertTrue(user.isPresent());
        assertEquals(carlInDb.getLogin(), user.get().getLogin());
    }

    @Test
    void findAll() {
        //when
        List<User> all = userService.findAll();
        //then
        assertEquals(3, all.size());
    }

    @Test
    void save() {
        //when
        User saved = userService.save(newBob);
        //then
        assertEquals(bobInDb.getLogin(), saved.getLogin());
        assertEquals(bobInDb.getLogin(), saved.getLogin());
        assertEquals(bobInDb.getLogin(), saved.getLogin());
    }

    @Test
    void delete(){
        User saved = userService.save(newBob);
        int count = userService.findAll().size();
        assertEquals(4,count);
        userService.delete(saved);
        count = userService.findAll().size();
        assertEquals(3,count);
    }

    @Test
    void deleteById(){
        User saved = userService.save(newBob);
        int count = userService.findAll().size();
        assertEquals(4,count);
        userService.deleteById(saved.getId());
        count = userService.findAll().size();
        assertEquals(3,count);
    }


}