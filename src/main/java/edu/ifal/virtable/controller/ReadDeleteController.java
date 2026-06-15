package edu.ifal.virtable.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.ifal.virtable.dto.DeleteResponse;
import edu.ifal.virtable.model.Item;
import edu.ifal.virtable.service.CrudService;

public abstract class ReadDeleteController<T extends Item> {
    protected final CrudService<T> service;

    public ReadDeleteController(CrudService<T> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<T>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> read(@PathVariable Long id) {
        return ResponseEntity.ok(service.read(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
