package com.bci.bcichallenge.controller;

import com.bci.bcichallenge.exceptions.EmailAlreadyExistsException;
import com.bci.bcichallenge.model.Phone;
import com.bci.bcichallenge.dto.UserRequest;
import com.bci.bcichallenge.dto.UserResponse;
import com.bci.bcichallenge.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@WebFluxTest(UserController.class)
class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @Test
    void whenCreateUserReturnUserResponse() {
        Phone phoneRequest = new Phone();
        phoneRequest.setNumber("12345678");
        phoneRequest.setCitycode("57");
        phoneRequest.setCitycode("1");
        UserRequest request = new UserRequest(
                "Juan Rodriguez",
                "juan@rodriguez.org",
                "Arnold123$",
                List.of(phoneRequest)
        );

        LocalDateTime now = LocalDateTime.now();

        Phone phoneResponse = new Phone();
        phoneResponse.setNumber("12345678");
        phoneResponse.setCitycode("57");
        phoneResponse.setCitycode("1");
        phoneResponse.setCreated(now);
        phoneResponse.setModified(now);

        UserResponse response = new UserResponse(
                UUID.fromString("98e9b54a-d5ab-422c-986d-c87cca03f01f"),
                "Juan Rodriguez",
                "juan@rodriguez.org",
                "Arnold123$",
                true,
                "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKdWFuIFJvZHJpZ3VleiIsImlhdCI6MTc0NjU5MDE2MSwiZXhwIjoxNzQ2NTkzNzYxfQ.s21qIb3sTB7vsUSSDqMdeJLEaiVDlaBW7G6Zp6aCsGaRsJ0j6XBGzY6NTDaJAhfj33T1AabRei9CUozgCw0TJw",
                now,
                now,
                now,
                List.of(phoneResponse)
        );

        Mockito.when(userService.registerIfNotExist(Mockito.any(UserRequest.class)))
                .thenReturn(Mono.just(response));

        webTestClient.post()
                .uri("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("98e9b54a-d5ab-422c-986d-c87cca03f01f")
                .jsonPath("$.email").isEqualTo("juan@rodriguez.org")
                .jsonPath("$.isActive").isEqualTo(true)
                .jsonPath("$.phones[0].number").isEqualTo("12345678");
    }

    @Test
    void whenCreateUserReturnConflict() {
        UserRequest request = new UserRequest(
                "Juan Rodriguez",
                "juan@rodriguez.org",
                "Arnold123$",
                List.of()
        );

        Mockito.when(userService.registerIfNotExist(Mockito.any(UserRequest.class)))
                .thenReturn(Mono.error(new EmailAlreadyExistsException("El correo ya ha sido registrado")));

        webTestClient.post()
                .uri("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody()
                .jsonPath("$.mensaje").isEqualTo("El correo ya ha sido registrado");
    }
}