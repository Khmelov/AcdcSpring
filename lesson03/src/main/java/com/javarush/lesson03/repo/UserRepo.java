package com.javarush.lesson03.repo;


import com.javarush.lesson03.config.SessionCreator;
import com.javarush.lesson03.entity.User;
import com.javarush.lesson03.processor.Watch;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
@ToString
public class UserRepo {

    private final SessionCreator sessionCreator;

    @Watch
    public Optional<User> getById(Long id) {
        Session session = sessionCreator.getSession();
        User user = session.get(User.class, id);
        return Optional.of(user);
    }

}
