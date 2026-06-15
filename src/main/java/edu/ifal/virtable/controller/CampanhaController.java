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

import edu.ifal.virtable.model.campanha.Campanha;
import edu.ifal.virtable.model.campanha.PersonagemCampanha;
import edu.ifal.virtable.model.campanha.UsuarioCampanha;
import edu.ifal.virtable.model.usuario.Usuario;
import edu.ifal.virtable.security.CustomUserDetails;
import edu.ifal.virtable.service.CampanhaService;
import edu.ifal.virtable.service.PersonagemCampanhaService;
import edu.ifal.virtable.service.UsuarioCampanhaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/campanhas")
public class CampanhaController extends ReadDeleteController<Campanha> {

    private final UsuarioCampanhaService usuarioCampanhaService;
    private final PersonagemCampanhaService personagemCampanhaService;

    public CampanhaController(CampanhaService campanhaService, UsuarioCampanhaService usuarioCampanhaService,
            PersonagemCampanhaService personagemCampanhaService) {
        super(campanhaService);
        this.usuarioCampanhaService = usuarioCampanhaService;
        this.personagemCampanhaService = personagemCampanhaService;
    }

    @PostMapping
    public ResponseEntity<Campanha> create(@AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody Campanha request) {
        request.setMestre(new Usuario(userDetails.getId(), null, null, null));
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campanha> update(
            @PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody Campanha request) {
        request.setMestre(new Usuario(userDetails.getId(), null, null, null));
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}/usuarios")
    public ResponseEntity<List<UsuarioCampanha>> listUsuarios(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioCampanhaService.listByIdCampanha(id));
    }

    @GetMapping("/{id}/personagens")
    public ResponseEntity<List<PersonagemCampanha>> listPersonagens(@PathVariable Long id) {
        return ResponseEntity.ok(personagemCampanhaService.listByIdCampanha(id));
    }
}
