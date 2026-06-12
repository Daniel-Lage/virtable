package edu.ifal.virtable.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifal.virtable.domain.campanha.UsuarioCampanha;
import edu.ifal.virtable.dto.CreateResponse;
import edu.ifal.virtable.dto.DeleteResponse;
import edu.ifal.virtable.dto.ListResponse;
import edu.ifal.virtable.dto.ReadResponse;
import edu.ifal.virtable.dto.UpdateRequest;
import edu.ifal.virtable.dto.UpdateResponse;
import edu.ifal.virtable.service.UsuarioCampanhaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios-campanhas")
public class UsuarioCampanhaController {

    private final UsuarioCampanhaService usuarioCampanhaService;

    public UsuarioCampanhaController(UsuarioCampanhaService usuarioCampanhaService) {
        this.usuarioCampanhaService = usuarioCampanhaService;
    }

    @PostMapping
    public ResponseEntity<CreateResponse<UsuarioCampanha>> create(
            @Valid @RequestBody UsuarioCampanha usuarioCampanha
    ) {
        return ResponseEntity.ok(usuarioCampanhaService.create(usuarioCampanha));
    }

    @GetMapping
    public ResponseEntity<ListResponse<UsuarioCampanha>> list() {
        return ResponseEntity.ok(usuarioCampanhaService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadResponse<UsuarioCampanha>> read(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioCampanhaService.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponse<UsuarioCampanha>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRequest<UsuarioCampanha> request
    ) {
        return ResponseEntity.ok(usuarioCampanhaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioCampanhaService.delete(id));
    }
}