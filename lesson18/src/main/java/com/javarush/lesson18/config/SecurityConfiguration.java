package com.javarush.lesson18.config;

import com.javarush.lesson18.entity.Role;
import com.javarush.lesson18.service.DatabaseUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Map;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private  final DatabaseUserDetailsService databaseUserDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
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
                .oauth2Login(login -> login
                        .loginPage("/login")
                        .successHandler(getAuthenticationSuccessHandler())
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login")
                )
                .build();
    }

    private AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
        return (req, res, auth) -> {
            DefaultOAuth2User principal = (DefaultOAuth2User) auth.getPrincipal();
            Map<String, Object> attributes = principal.getAttributes();
            String login = attributes.entrySet().stream()
                    .filter(e -> "login".equals(e.getKey()) || "email".equals(e.getKey()))
                    .map(e -> e.getValue().toString().replaceAll("@.*", ""))
                    .findFirst()
                    .orElse("notFoundUser");
            try {
                UserDetails userDetails = databaseUserDetailsService.loadUserByUsername(login);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                res.sendRedirect("/users");
            } catch (Exception e) {
                res.sendRedirect("/login?notFoundLogin="+login);
            }
        };
    }


}
