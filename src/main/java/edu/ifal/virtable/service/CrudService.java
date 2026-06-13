package edu.ifal.virtable.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifal.virtable.dto.DeleteResponse;
import jakarta.validation.Valid;

public abstract class CrudService<T> {

    protected final JpaRepository<T, Long> repository;

    public CrudService(JpaRepository<T, Long> repository) {
        this.repository = repository;

    }

    public T create(@Valid T item) {
        T itemSalvo = repository.save(item);
        return itemSalvo;
    }

    public List<T> list() {
        return repository.findAll();
    }

    public T read(Long id) {
        T item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        return item;
    }

    public T update(Long id, @Valid T request) {
        T itemAlterado = request;

        if (!repository.existsById(id)) {
            throw new RuntimeException("Item não encontrado");
        }

        repository.save(itemAlterado);
        return itemAlterado;
    }

    public DeleteResponse delete(Long id) {
        T item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        repository.delete(item);
        return new DeleteResponse(String.format("Removeu %s com id %s", item.getClass(), id));
    }
}
