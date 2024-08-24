package com.javarush.lesson18.service;

import com.javarush.lesson18.repository.Repo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DatabaseUserDetailsService implements UserDetailsService {

    private final Repo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByLogin(username).map(u ->
                        User.withUsername(username)
                                .password(u.getPassword())
                                .authorities(u.getRole())
                                .build())
                .orElseThrow(() -> new UsernameNotFoundException("login not found:" + username));
    }
}
