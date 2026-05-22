package edu.ifal.virtable.dto;

public record RegisterRequest(
        String nome,
        String email,
        String senha
) {}