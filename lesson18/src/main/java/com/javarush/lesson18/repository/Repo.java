package com.javarush.lesson18.repository;

import com.javarush.lesson18.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Repo extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
}
