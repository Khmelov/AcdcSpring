package com.javarush.lesson18.controller.http;

import com.javarush.lesson18.annotation.MyContext;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MyContext
@AutoConfigureMockMvc
@RequiredArgsConstructor
@WithMockUser
class UserControllerTest {

    private final MockMvc mockMvc;

    @Test
    void showAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType("text/html;charset=UTF-8")
                );

    }

    @Test
    void showOneUserAndUsers() {
    }

    @Test
    void processNewUserOrLogin() {
    }

    @Test
    void updateOrDeleteUser() {
    }
}