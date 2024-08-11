package com.javarush.lesson14.dto;

import com.javarush.lesson14.entity.Role;
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
