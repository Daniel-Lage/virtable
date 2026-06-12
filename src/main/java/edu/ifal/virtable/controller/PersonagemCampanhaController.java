package edu.ifal.virtable.controller;

import edu.ifal.virtable.domain.campanha.PersonagemCampanha;
import edu.ifal.virtable.dto.CreateResponse;
import edu.ifal.virtable.dto.DeleteResponse;
import edu.ifal.virtable.dto.ListResponse;
import edu.ifal.virtable.dto.ReadResponse;
import edu.ifal.virtable.dto.UpdateRequest;
import edu.ifal.virtable.dto.UpdateResponse;
import edu.ifal.virtable.service.PersonagemCampanhaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personagens-campanhas")
public class PersonagemCampanhaController {

    private final PersonagemCampanhaService personagemCampanhaService;

    public PersonagemCampanhaController(PersonagemCampanhaService personagemCampanhaService) {
        this.personagemCampanhaService = personagemCampanhaService;
    }

    @PostMapping
    public ResponseEntity<CreateResponse<PersonagemCampanha>> create(
            @Valid @RequestBody PersonagemCampanha personagemCampanha
    ) {
        return ResponseEntity.ok(personagemCampanhaService.create(personagemCampanha));
    }

    @GetMapping
    public ResponseEntity<ListResponse<PersonagemCampanha>> list() {
        return ResponseEntity.ok(personagemCampanhaService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadResponse<PersonagemCampanha>> read(@PathVariable Long id) {
        return ResponseEntity.ok(personagemCampanhaService.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponse<PersonagemCampanha>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRequest<PersonagemCampanha> request
    ) {
        return ResponseEntity.ok(personagemCampanhaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(personagemCampanhaService.delete(id));
    }
}