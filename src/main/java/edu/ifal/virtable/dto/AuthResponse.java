package edu.ifal.virtable.dto;

/// Interface selada para representar a resposta para reqests de autenticação, que pode ser sucesso ou erro
public sealed interface AuthResponse permits AuthSuccessResponse, AuthErrorResponse {
}