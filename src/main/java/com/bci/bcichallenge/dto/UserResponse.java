package com.bci.bcichallenge.dto;

import com.bci.bcichallenge.model.Phone;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        String password,
        boolean isActive,
        String token,
        LocalDateTime created,
        LocalDateTime modified,
        LocalDateTime lastLogin,
        List<Phone> phones
) {}
