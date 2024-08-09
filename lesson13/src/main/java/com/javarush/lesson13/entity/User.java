package com.javarush.lesson13.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@ToString
public class User {

    @Id
    private Long id;

    private String login;

    private String password;

    private Role role;

}
