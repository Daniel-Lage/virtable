package edu.ifal.virtable.dto;

public record AuthErrorResponse(
        String error) implements AuthResponse {
}