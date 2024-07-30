package com.javarush.lesson11.repository;

import com.javarush.lesson11.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<User, Long> {


}
