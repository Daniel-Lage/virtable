package edu.ifal.virtable.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
                @Email(message = "email: campo deve ser um email válido") @NotBlank(message = "email: campo não pode ser vazio") String email,
                @NotBlank(message = "senha: campo não pode ser vazio") String senha) {
}