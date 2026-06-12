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

import edu.ifal.virtable.domain.sistema.SistemaRPG;
import edu.ifal.virtable.dto.CreateResponse;
import edu.ifal.virtable.dto.DeleteResponse;
import edu.ifal.virtable.dto.ListResponse;
import edu.ifal.virtable.dto.ReadResponse;
import edu.ifal.virtable.dto.UpdateRequest;
import edu.ifal.virtable.dto.UpdateResponse;
import edu.ifal.virtable.service.SistemaRPGService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/sistemas-rpg")
public class SistemaRPGController {

    private final SistemaRPGService sistemaRPGService;

    public SistemaRPGController(SistemaRPGService sistemaRPGService) {
        this.sistemaRPGService = sistemaRPGService;
    }

    @PostMapping
    public ResponseEntity<CreateResponse<SistemaRPG>> create(
            @Valid @RequestBody SistemaRPG sistemaRPG
    ) {
        return ResponseEntity.ok(sistemaRPGService.create(sistemaRPG));
    }

    @GetMapping
    public ResponseEntity<ListResponse<SistemaRPG>> list() {
        return ResponseEntity.ok(sistemaRPGService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadResponse<SistemaRPG>> read(@PathVariable Long id) {
        return ResponseEntity.ok(sistemaRPGService.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponse<SistemaRPG>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRequest<SistemaRPG> request
    ) {
        return ResponseEntity.ok(sistemaRPGService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(sistemaRPGService.delete(id));
    }
}