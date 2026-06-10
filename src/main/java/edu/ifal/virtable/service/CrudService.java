package edu.ifal.virtable.service;

import edu.ifal.virtable.dto.DeleteResponse;
import edu.ifal.virtable.dto.ListResponse;
import edu.ifal.virtable.dto.ReadResponse;
import edu.ifal.virtable.dto.UpdateRequest;
import edu.ifal.virtable.dto.UpdateResponse;
import jakarta.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CrudService<T> {

    private final JpaRepository<T, Long> repository;

    public CrudService(
            JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public ListResponse<T> list() {
        return new ListResponse<T>(repository.findAll());
    }

    public ReadResponse<T> read(Long id) {
        T item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new ReadResponse<T>(item);
    }

    public UpdateResponse<T> update(Long id, @Valid UpdateRequest<T> request) {
        T itemAlterado = request.value();

        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }

        repository.save(itemAlterado);
        return new UpdateResponse<T>(itemAlterado);
    }

    public DeleteResponse delete(Long id) {
        T item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        repository.delete(item);
        return new DeleteResponse(true);
    }
}