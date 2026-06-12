package edu.ifal.virtable.service;

import edu.ifal.virtable.dto.CreateResponse;
import edu.ifal.virtable.dto.DeleteResponse;
import edu.ifal.virtable.dto.ListResponse;
import edu.ifal.virtable.dto.ReadResponse;
import edu.ifal.virtable.dto.UpdateRequest;
import edu.ifal.virtable.dto.UpdateResponse;
import jakarta.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CrudService<T> {

    private final JpaRepository<T, Long> repository;

    public CrudService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public CreateResponse<T> create(@Valid T item) {
        T itemSalvo = repository.save(item);
        return new CreateResponse<T>(itemSalvo);
    }

    public ListResponse<T> list() {
        return new ListResponse<T>(repository.findAll());
    }

    public ReadResponse<T> read(Long id) {
        T item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        return new ReadResponse<T>(item);
    }

    public UpdateResponse<T> update(Long id, @Valid UpdateRequest<T> request) {
        T itemAlterado = request.value();

        if (!repository.existsById(id)) {
            throw new RuntimeException("Item não encontrado");
        }

        repository.save(itemAlterado);
        return new UpdateResponse<T>(itemAlterado);
    }

    public DeleteResponse delete(Long id) {
        T item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        repository.delete(item);
        return new DeleteResponse(true);
    }
}