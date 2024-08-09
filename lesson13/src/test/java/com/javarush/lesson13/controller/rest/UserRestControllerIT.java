package com.javarush.lesson13.controller.rest;

import com.javarush.lesson13.dto.UserTo;
import com.javarush.lesson13.entity.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRestControllerIT {

    public static final int USER_ID = 1;
    @Autowired
    WebTestClient webTestClient;

    @Test
    void findAll() {
        webTestClient
                .get()
                .uri(UserRestController.ENDPOINT_USERS)
                .header("accept", MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(UserTo.class)
                .hasSize(3);
    }

    @Test
    void get() {
        UserTo userFromRest = webTestClient
                .get()
                .uri(UserRestController.ENDPOINT_USERS + "/{id}", USER_ID)
                .header("accept", MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(UserTo.class)
                .returnResult().getResponseBody();
        Assertions.assertNotNull(userFromRest);
        Assertions.assertEquals("Carl", userFromRest.getLogin());
    }

    @Test
    void createUpdateDelete() {
        UserTo user = UserTo.builder()
                .login("testLogin")
                .password("123")
                .role(Role.GUEST)
                .build();
        UserTo userInDb = webTestClient
                .post()
                .uri(UserRestController.ENDPOINT_USERS)
                .header("accept", MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(user)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(UserTo.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(userInDb);
        userInDb.setLogin("update_test");

        UserTo userInDbAfterUpdate = webTestClient
                .put()
                .uri(UserRestController.ENDPOINT_USERS+"/{id}", userInDb.getId())
                .header("accept", MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(userInDb)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(UserTo.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertNotNull(userInDbAfterUpdate);
        Assertions.assertEquals(userInDb.getLogin(), userInDbAfterUpdate.getLogin());

         webTestClient
                .delete()
                .uri(UserRestController.ENDPOINT_USERS+"/{id}", userInDb.getId())
                .header("accept", MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }
}