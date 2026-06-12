package edu.ifal.virtable.controller;

import edu.ifal.virtable.domain.usuario.Usuario;
import edu.ifal.virtable.dto.DeleteResponse;
import edu.ifal.virtable.security.CustomUserDetails;
import edu.ifal.virtable.service.UsuarioService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eu")
public class EuController {

    private final UsuarioService usuarioService;

    public EuController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<Usuario> read(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(usuarioService.read(userDetails.getId()));
    }

    @PutMapping
    public ResponseEntity<Usuario> update(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody @Valid Usuario request) {
        return ResponseEntity.ok(usuarioService.update(userDetails.getId(), request));
    }

    @DeleteMapping
    public ResponseEntity<DeleteResponse> delete(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(usuarioService.delete(userDetails.getId()));
    }
}