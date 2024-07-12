package com.javarush.lesson06.repo;


import com.javarush.lesson06.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
