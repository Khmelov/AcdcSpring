package com.javarush.lesson10.repository;

import com.javarush.lesson10.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<User, Long> {


}
