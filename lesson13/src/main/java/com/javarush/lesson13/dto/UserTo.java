package com.javarush.lesson13.dto;

import com.javarush.lesson13.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTo {

    private Long id;

    private String login;

    private String password;

    private Role role;

}
