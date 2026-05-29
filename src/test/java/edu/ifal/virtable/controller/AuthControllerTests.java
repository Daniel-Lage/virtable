package edu.ifal.virtable.controller;

import edu.ifal.virtable.dto.AuthResponse;
import edu.ifal.virtable.dto.LoginRequest;
import edu.ifal.virtable.dto.RegisterRequest;
import edu.ifal.virtable.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthControllerTests {

    private final AuthService authService;

    public AuthControllerTests(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<AuthResponse> cadastrar(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.cadastrar(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}