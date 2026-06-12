package edu.ifal.virtable.controller;

import edu.ifal.virtable.dto.AuthResponse;
import edu.ifal.virtable.dto.LoginRequest;
import edu.ifal.virtable.dto.RegisterRequest;
import edu.ifal.virtable.service.UsuarioService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<AuthResponse> cadastrar(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(usuarioService.cadastrar(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(usuarioService.login(request));
    }
}