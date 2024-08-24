package com.javarush.lesson18.service;

import com.javarush.lesson18.dto.UserTo;
import com.javarush.lesson18.entity.User;
import com.javarush.lesson18.map.Mapper;
import com.javarush.lesson18.repository.Repo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final Repo repo;

    private final PasswordEncoder passwordEncoder;

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
        user.setPassword(passwordEncoder.encode(userTo.getPassword()));
        user = repo.saveAndFlush(user);
        return Mapper.map(user);
    }

    @Transactional
    public void delete(UserTo userTo) {
        Optional<User> inDbUser = repo.findByLogin(userTo.getLogin());
        if (inDbUser.isPresent()) {
            String password = inDbUser.get().getPassword();
            boolean matches = passwordEncoder.matches(userTo.getPassword(), password);
            if (matches) {
                repo.delete(Mapper.map(userTo));
            }
        }
    }

    @Transactional
    public void deleteById(Long id) {
        repo.deleteById(id);
    }


}
