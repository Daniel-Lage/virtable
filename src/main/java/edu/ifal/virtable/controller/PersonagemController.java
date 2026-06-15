package edu.ifal.virtable.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifal.virtable.model.campanha.PersonagemCampanha;
import edu.ifal.virtable.model.ficha.ValorCampo;
import edu.ifal.virtable.model.personagem.Personagem;
import edu.ifal.virtable.model.usuario.Usuario;
import edu.ifal.virtable.security.CustomUserDetails;
import edu.ifal.virtable.service.PersonagemCampanhaService;
import edu.ifal.virtable.service.PersonagemService;
import edu.ifal.virtable.service.ValorCampoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/personagens")
public class PersonagemController extends ReadDeleteController<Personagem> {

    private final PersonagemCampanhaService personagemCampanhaService;
    private final ValorCampoService valorCampoService;

    public PersonagemController(PersonagemService personagemService, ValorCampoService valorCampoService,
            PersonagemCampanhaService personagemCampanhaService) {
        super(personagemService);
        this.valorCampoService = valorCampoService;
        this.personagemCampanhaService = personagemCampanhaService;
    }

    @PostMapping
    public ResponseEntity<Personagem> create(@AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody Personagem item) {
        item.setCriador(new Usuario(userDetails.getId(), null, null, null));
        return ResponseEntity.ok(service.create(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personagem> update(
            @PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody Personagem request) {
        request.setCriador(new Usuario(userDetails.getId(), null, null, null));
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}/valores-campo")
    public ResponseEntity<List<ValorCampo>> listValoresCampo(@PathVariable Long id) {
        return ResponseEntity.ok(valorCampoService.listByIdCampoFicha(id));
    }

    @GetMapping("/{id}/campanhas")
    public ResponseEntity<List<PersonagemCampanha>> listCampanhasParticipadas(@PathVariable Long id) {
        return ResponseEntity.ok(personagemCampanhaService.listByIdPersonagem(id));
    }
}