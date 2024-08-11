package com.javarush.lesson14.controller.rest;

import com.javarush.lesson14.dto.UserTo;
import com.javarush.lesson14.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class UserToHandler {

    private final UserService userToService;



    public Mono<ServerResponse> listUserTos(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userToService.findAll(), UserTo.class);
    }

    public Mono<ServerResponse> addNewUserTo(ServerRequest serverRequest) {
        Mono<UserTo> userToMono = serverRequest.bodyToMono(UserTo.class);
        return userToMono.flatMap(userTo ->
                ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(userToService.save(userTo), UserTo.class));

    }

    public Mono<ServerResponse> updateUserTo(ServerRequest serverRequest) {
        Mono<UserTo> userToMono = serverRequest.bodyToMono(UserTo.class);

        return userToMono.flatMap(userTo ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(userToService.save(userTo), UserTo.class));
    }

    public Mono<ServerResponse> deleteUserTo(ServerRequest serverRequest) {
        final long userToId = Long.parseLong(serverRequest.pathVariable("id"));
        return userToService
                .get(userToId)
                .flatMap(s -> ServerResponse.noContent().build(userToService.delete(s)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
