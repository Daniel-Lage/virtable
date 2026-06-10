package edu.ifal.virtable.dto;

public record RuntimeErrorResponse(
        String error, String message) {
}