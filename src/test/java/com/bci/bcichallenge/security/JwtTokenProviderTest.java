package com.bci.bcichallenge.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtTokenProviderTest {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void testGenerateToken() {
        String username = "testuser";

        String token = jwtTokenProvider.generateToken(username);

        assertNotNull(token);
    }
}