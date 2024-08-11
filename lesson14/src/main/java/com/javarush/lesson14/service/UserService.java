package com.javarush.lesson14.service;

import com.javarush.lesson14.dto.UserTo;
import com.javarush.lesson14.entity.User;
import com.javarush.lesson14.map.Mapper;
import com.javarush.lesson14.repository.Repo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final Repo repo;

    public Mono<UserTo> get(Long id) {
        return repo
                .findById(id)
                .map(Mapper::map);
    }

    public Flux<UserTo> findAll() {
        return repo
                .findAll(Sort.sort(User.class).by(User::getId))
                .map(Mapper::map);

    }

    @Transactional
    public Mono<UserTo> save(UserTo userTo) {
        return repo
                .save(Mapper.map(userTo))
                .map(Mapper::map);
    }

    @Transactional
    public Mono<Void> delete(UserTo userTo) {
        return repo.delete(Mapper.map(userTo));
    }

    @Transactional
    public Mono<Void>  deleteById(Long id) {
        return repo.deleteById(id);
    }


}
