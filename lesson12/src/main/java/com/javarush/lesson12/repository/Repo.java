package com.javarush.lesson12.repository;

import com.javarush.lesson12.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<User, Long> {


}
