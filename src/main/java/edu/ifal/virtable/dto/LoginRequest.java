package edu.ifal.virtable.dto;

public record LoginRequest(
        String email,
        String senha
) {}