package com.javarush.lesson17.dto;

import com.javarush.lesson17.entity.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTo {

    @Positive
    private Long id;

    @Size(min = 3, max = 42)
    private String login;

    @Size(min = 3, max = 42)
    private String password;

    @NotNull
    private Role role;

}
