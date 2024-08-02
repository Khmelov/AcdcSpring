package com.javarush.lesson12.service;

import com.javarush.lesson12.dto.UserTo;
import com.javarush.lesson12.entity.Role;
import com.javarush.lesson12.entity.User;
import com.javarush.lesson12.repository.Repo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    public static final long USER_ID = 1L;
    @Mock
    Repo repo;
    @InjectMocks UserService userService;

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
            .password("456")
            .role(Role.GUEST)
            .build();

    final UserTo newBob = UserTo.builder()
            .login("Bob")
            .password("456")
            .role(Role.GUEST)
            .build();


    @Test
    void get() {
        //given
        when(repo.findById(USER_ID)).thenReturn(Optional.of(carlInDb));
        //when
        Optional<UserTo> user = userService.get(USER_ID);
        //then
        assertTrue(user.isPresent());
        assertEquals(carlInDb.getLogin(), user.get().getLogin());
        verify(repo, Mockito.times(1)).findById(USER_ID);
    }

    @Test
    void findAll() {
        //given
        List<User> userList = List.of(carlInDb, alisaInDb, bobInDb);
        when(repo.findAll(any(Sort.class))).thenReturn(userList);
        //when
        List<UserTo> all = userService.findAll();
        //then
        assertEquals(userList.size(), all.size());
    }

    @Test
    void save() {
        //given
        when(repo.saveAndFlush(Mockito.any(User.class))).thenReturn(bobInDb);
        //when
        UserTo saved = userService.save(newBob);
        //then
        verify(repo, Mockito.times(1)).saveAndFlush(any(User.class));
    }



}