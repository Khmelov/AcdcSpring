package com.javarush.lesson14.controller.rest;

import com.javarush.lesson14.map.Mapper;
import com.javarush.lesson14.repository.Repo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class UserRestFunctionEndPoint {

    public static final String ENDPOINT_USERS_V2 = "/restapi/v2/users";
    public static final String ID_PATTERN = "/{id:[0-9]+}";


    @Bean
    public RouterFunction<ServerResponse> route(UserToHandler userToHandler, Repo repo) {
        return RouterFunctions
                .route(
                        GET(ENDPOINT_USERS_V2 + ID_PATTERN)
                                .and(accept(APPLICATION_JSON)),
                        req -> repo
                                .findById(getId(req))
                                .map(Mapper::map)
                                .flatMap(userTo -> ServerResponse.ok().body(BodyInserters.fromValue(userTo)))
                                .switchIfEmpty(ServerResponse.notFound().build()))
                .andRoute(
                        GET(ENDPOINT_USERS_V2)
                                .and(accept(APPLICATION_JSON)), userToHandler::listUserTos)
                .andRoute(
                        POST(ENDPOINT_USERS_V2)
                                .and(accept(APPLICATION_JSON)), userToHandler::addNewUserTo)
                .andRoute(
                        PUT(ENDPOINT_USERS_V2 + ID_PATTERN)
                                .and(accept(APPLICATION_JSON)), userToHandler::updateUserTo)
                .andRoute(
                        DELETE(ENDPOINT_USERS_V2 + ID_PATTERN)
                                .and(accept(APPLICATION_JSON)), userToHandler::deleteUserTo);
    }

    private static long getId(ServerRequest req) {
        return Long.parseLong(req.pathVariable("id"));
    }

}
