package com.javarush.lesson13.repository;

import com.javarush.lesson13.entity.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface Repo extends R2dbcRepository<User, Long> {


}
