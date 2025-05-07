package com.bci.bcichallenge.service;

import com.bci.bcichallenge.exceptions.EmailAlreadyExistsException;
import com.bci.bcichallenge.dto.UserRequest;
import com.bci.bcichallenge.dto.UserResponse;
import com.bci.bcichallenge.mapper.UserMapper;
import com.bci.bcichallenge.model.User;
import com.bci.bcichallenge.repository.UserRepository;
import com.bci.bcichallenge.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    public Mono<UserResponse> registerIfNotExist(UserRequest userRequest) {
        return Mono.fromCallable(() -> userRepository.findByEmail(userRequest.email()))
                .map(existingUser -> {
                    if (existingUser.isPresent()) {
                        throw new EmailAlreadyExistsException("El correo ya ha sido registrado");
                    }
                    User user = userRepository.save(userMapper.toEntity(userRequest));
                    user.setToken(jwtTokenProvider.generateToken(user.getName()));
                    return userMapper.toUserResponse(user);
                });
    }

}
