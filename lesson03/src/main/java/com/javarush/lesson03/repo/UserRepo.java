package com.javarush.lesson03.repo;


import com.javarush.lesson03.config.SessionCreator;
import com.javarush.lesson03.entity.User;
import com.javarush.lesson03.processor.Watch;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
        try (session) {
            Transaction tx = session.beginTransaction();
            User user = session.get(User.class, id);
            tx.commit();
            return Optional.of(user);
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

}
