package com.javarush.lesson14.repository;

import com.javarush.lesson14.entity.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface Repo extends R2dbcRepository<User, Long> {


}
