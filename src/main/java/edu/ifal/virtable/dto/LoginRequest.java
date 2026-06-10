package edu.ifal.virtable.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull(message = "Não deve ser nulo") @NotBlank(message = "Não deve estar em branco") @Email(message = "Deve ser um email válido") String email,
        @NotNull(message = "Não deve ser nulo") @NotBlank(message = "Não deve estar em branco") String senha) {
}