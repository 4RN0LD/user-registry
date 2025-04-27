package com.bci.bcichallenge.controller;

import com.bci.bcichallenge.dto.UserRequest;
import com.bci.bcichallenge.dto.UserResponse;
import com.bci.bcichallenge.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping()
    Mono<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.registerIfNotExist(userRequest);
    }
}
