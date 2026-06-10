package edu.ifal.virtable.controller;

import edu.ifal.virtable.dto.FieldErrorResponse;
import edu.ifal.virtable.dto.RuntimeErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<FieldErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
                return ResponseEntity.badRequest()
                                .body(new FieldErrorResponse("Erro de Validação",
                                                ex.getBindingResult().getFieldErrors().stream()
                                                                .collect(Collectors.groupingBy(
                                                                                error -> error.getField(),
                                                                                Collectors.mapping(
                                                                                                error -> error.getDefaultMessage(),
                                                                                                Collectors.toList())))));
        }

        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
        public ResponseEntity<RuntimeErrorResponse> handleMethodNotSupported(
                        HttpRequestMethodNotSupportedException ex) {
                String method = ex.getMethod();
                String supported = ex.getSupportedHttpMethods() != null ? ex.getSupportedHttpMethods().toString()
                                : "[]";
                String message = String.format("Método %s não é suportado nesta rota. Métodos permitidos: %s", method,
                                supported);
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                                .body(new RuntimeErrorResponse("Erro de método não suportado", message));
        }

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<RuntimeErrorResponse> handleRuntimeException(RuntimeException ex) {
                final String errorMessage = ex.getMessage();
                return ResponseEntity.internalServerError()
                                .body(new RuntimeErrorResponse(
                                                "Erro de Tempo de Execução",
                                                errorMessage != null ? errorMessage : "Erro não identificado"));
        }

}