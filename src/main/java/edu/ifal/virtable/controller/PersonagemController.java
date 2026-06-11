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

import edu.ifal.virtable.domain.personagem.Personagem;
import edu.ifal.virtable.dto.CreateResponse;
import edu.ifal.virtable.dto.DeleteResponse;
import edu.ifal.virtable.dto.ListResponse;
import edu.ifal.virtable.dto.ReadResponse;
import edu.ifal.virtable.dto.UpdateRequest;
import edu.ifal.virtable.dto.UpdateResponse;
import edu.ifal.virtable.service.PersonagemService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    private final PersonagemService personagemService;

    public PersonagemController(PersonagemService personagemService) {
        this.personagemService = personagemService;
    }

    @PostMapping
    public ResponseEntity<CreateResponse<Personagem>> create(
            @Valid @RequestBody Personagem personagem
    ) {
        return ResponseEntity.ok(personagemService.create(personagem));
    }

    @GetMapping
    public ResponseEntity<ListResponse<Personagem>> list() {
        return ResponseEntity.ok(personagemService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadResponse<Personagem>> read(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponse<Personagem>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRequest<Personagem> request
    ) {
        return ResponseEntity.ok(personagemService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.delete(id));
    }
}