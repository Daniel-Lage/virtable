package edu.ifal.virtable.controller;

import edu.ifal.virtable.domain.usuario.Usuario;
import edu.ifal.virtable.dto.DeleteResponse;
import edu.ifal.virtable.dto.ListResponse;
import edu.ifal.virtable.dto.ReadResponse;
import edu.ifal.virtable.dto.UpdateRequest;
import edu.ifal.virtable.dto.UpdateResponse;
import edu.ifal.virtable.service.UsuarioService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<ListResponse<Usuario>> list() {
        return ResponseEntity.ok(usuarioService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadResponse<Usuario>> read(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponse<Usuario>> update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateRequest<Usuario> request) {
        return ResponseEntity.ok(usuarioService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.delete(id));
    }
}