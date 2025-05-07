package com.bci.bcichallenge.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GlobalExceptionResponse {
    private String mensaje;
}
