package com.javarush.lesson18.config;

import com.javarush.lesson18.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                //.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(r -> r
                        .requestMatchers("login").permitAll()
                        .requestMatchers(HttpMethod.POST).hasAnyAuthority(
                                Role.ADMIN.getAuthority(),
                                Role.USER.getAuthority())
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/users", true)
                        .failureForwardUrl("/login?error=true"))
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login")
                )
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
