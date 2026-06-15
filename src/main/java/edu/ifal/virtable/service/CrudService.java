package edu.ifal.virtable.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifal.virtable.dto.DeleteResponse;
import edu.ifal.virtable.model.Item;
import jakarta.validation.Valid;

public abstract class CrudService<T extends Item> {

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
        if (!repository.existsById(id)) {
            throw new RuntimeException("Item não encontrado");
        }

        request.setId(id);

        repository.save(request);
        return request;
    }

    public DeleteResponse delete(Long id) {
        T item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        repository.delete(item);
        return new DeleteResponse(String.format("Removeu %s com id %s", item.getClass(), id));
    }
}
