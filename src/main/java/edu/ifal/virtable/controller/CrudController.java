package edu.ifal.virtable.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.ifal.virtable.dto.DeleteResponse;
import edu.ifal.virtable.model.Item;
import edu.ifal.virtable.service.CrudService;
import jakarta.validation.Valid;

public abstract class CrudController<T extends Item> {
    protected final CrudService<T> service;

    public CrudController(CrudService<T> service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<T> create(
            @Valid @RequestBody T item) {
        return ResponseEntity.ok(service.create(item));
    }

    @GetMapping
    public ResponseEntity<List<T>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> read(@PathVariable Long id) {
        return ResponseEntity.ok(service.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(
            @PathVariable Long id,
            @Valid @RequestBody T request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
