package com.javarush.lesson12.service;

import com.javarush.lesson12.annotation.MyContext;
import com.javarush.lesson12.dto.UserTo;
import com.javarush.lesson12.entity.Role;
import com.javarush.lesson12.repository.Repo;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MyContext
@RequiredArgsConstructor
class UserServiceIT {

    public static final long USER_ID = 1L;

    private final UserService userService;

    final UserTo carlInDb = UserTo.builder()
            .id(1L)
            .login("Carl")
            .password("123")
            .role(Role.ADMIN)
            .build();

    final UserTo alisaInDb = UserTo.builder()
            .id(2L)
            .login("Alisa")
            .password("789")
            .role(Role.USER)
            .build();

    final UserTo bobInDb = UserTo.builder()
            .id(123L)
            .login("Bob")
            .role(Role.GUEST)
            .password("456")
            .build();

    final UserTo newBob = UserTo.builder()
            .login("Bob")
            .password("456")
            .role(Role.GUEST)
            .build();
    @Autowired
    private Repo repo;


    @Test
    void get() {
        //when
        Optional<UserTo> user = userService.get(USER_ID);
        //then
        assertTrue(user.isPresent());
        assertEquals(carlInDb.getLogin(), user.get().getLogin());
    }

    @Test
    void findAll() {
        //when
        List<UserTo> all = userService.findAll();
        //then
        assertEquals(3, all.size());
    }

    @Test
    void save() {
        //when
        UserTo saved = userService.save(newBob);
        //then
        assertEquals(bobInDb.getLogin(), saved.getLogin());
        assertEquals(bobInDb.getLogin(), saved.getLogin());
        assertEquals(bobInDb.getLogin(), saved.getLogin());
    }

    @Test
    void delete() {
        UserTo saved = userService.save(newBob);
        int count = userService.findAll().size();
        assertEquals(4, count);
        userService.delete(saved);
        count = userService.findAll().size();
        assertEquals(3, count);
    }

    @Test
    void deleteById() {
        UserTo saved = userService.save(newBob);
        int count = userService.findAll().size();
        assertEquals(4, count);
        userService.deleteById(saved.getId());
        count = userService.findAll().size();
        assertEquals(3, count);
    }


}