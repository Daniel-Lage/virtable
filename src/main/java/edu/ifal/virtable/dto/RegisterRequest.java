package edu.ifal.virtable.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
                @NotBlank(message = "campo não pode ser vazio") String nome,
                @Email(message = "campo deve ser um email válido") @NotBlank(message = "campo não pode ser vazio") String email,
                @NotBlank(message = "campo não pode ser vazio") String senha) {
}