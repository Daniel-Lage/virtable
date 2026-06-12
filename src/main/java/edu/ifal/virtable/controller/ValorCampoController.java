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

import edu.ifal.virtable.domain.ficha.ValorCampo;
import edu.ifal.virtable.dto.CreateResponse;
import edu.ifal.virtable.dto.DeleteResponse;
import edu.ifal.virtable.dto.ListResponse;
import edu.ifal.virtable.dto.ReadResponse;
import edu.ifal.virtable.dto.UpdateRequest;
import edu.ifal.virtable.dto.UpdateResponse;
import edu.ifal.virtable.service.ValorCampoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/valores-campos")
public class ValorCampoController {

    private final ValorCampoService valorCampoService;

    public ValorCampoController(ValorCampoService valorCampoService) {
        this.valorCampoService = valorCampoService;
    }

    @PostMapping
    public ResponseEntity<CreateResponse<ValorCampo>> create(
            @Valid @RequestBody ValorCampo valorCampo
    ) {
        return ResponseEntity.ok(valorCampoService.create(valorCampo));
    }

    @GetMapping
    public ResponseEntity<ListResponse<ValorCampo>> list() {
        return ResponseEntity.ok(valorCampoService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadResponse<ValorCampo>> read(@PathVariable Long id) {
        return ResponseEntity.ok(valorCampoService.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponse<ValorCampo>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRequest<ValorCampo> request
    ) {
        return ResponseEntity.ok(valorCampoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(valorCampoService.delete(id));
    }
}