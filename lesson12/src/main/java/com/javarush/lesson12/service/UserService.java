package com.javarush.lesson12.service;

import com.javarush.lesson12.dto.UserTo;
import com.javarush.lesson12.entity.User;
import com.javarush.lesson12.map.Mapper;
import com.javarush.lesson12.repository.Repo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final Repo repo;

    public Optional<UserTo> get(Long id) {
        return repo.findById(id).map(Mapper::map);
    }

    public List<UserTo> findAll() {
        Sort sort = Sort.sort(User.class).by(User::getId);
        return repo.findAll(sort)
                .stream()
                .map(Mapper::map)
                .toList();
    }

    @Transactional
    public UserTo save(UserTo userTo) {
        User user = Mapper.map(userTo);
        user = repo.saveAndFlush(user);
        return Mapper.map(user);
    }

    @Transactional
    public void delete(UserTo userTo) {
        repo.delete(Mapper.map(userTo));
    }

    @Transactional
    public void deleteById(Long id) {
        repo.deleteById(id);
    }


}
