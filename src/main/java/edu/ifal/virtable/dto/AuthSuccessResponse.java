package edu.ifal.virtable.dto;

public record AuthSuccessResponse(
        String token) implements AuthResponse {
}