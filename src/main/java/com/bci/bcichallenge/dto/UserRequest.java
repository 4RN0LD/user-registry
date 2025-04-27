package com.bci.bcichallenge.dto;

import com.bci.bcichallenge.model.Phone;
import com.bci.bcichallenge.validator.PasswordConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record UserRequest(
        String name,
        @NotBlank(message = "El correo no puede estar vacío")
        @Pattern(
                regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
                message = "El correo electrónico no tiene un formato válido"
        )
        String email,

        @PasswordConstraint(message = "La contrasena no cumple con los requisitos de seguridad")
        String password,
        List<Phone> phones
) {}
