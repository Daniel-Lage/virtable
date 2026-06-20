package edu.ifal.virtable.controller;

import edu.ifal.virtable.model.campanha.Campanha;
import edu.ifal.virtable.model.campanha.UsuarioCampanha;
import edu.ifal.virtable.model.personagem.Personagem;
import edu.ifal.virtable.model.usuario.Usuario;
import edu.ifal.virtable.service.CampanhaService;
import edu.ifal.virtable.service.PersonagemService;
import edu.ifal.virtable.service.UsuarioCampanhaService;
import edu.ifal.virtable.service.UsuarioService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends ReadDeleteController<Usuario> {
    private final PersonagemService personagemService;
    private final UsuarioCampanhaService usuarioCampanhaService;
    private final CampanhaService campanhaService;

    public UsuarioController(UsuarioService usuarioService, PersonagemService personagemService,
            CampanhaService campanhaService, UsuarioCampanhaService usuarioCampanhaService) {
        super(usuarioService);
        this.personagemService = personagemService;
        this.campanhaService = campanhaService;
        this.usuarioCampanhaService = usuarioCampanhaService;

    }

    @GetMapping("/{id}/personagens")
    public ResponseEntity<List<Personagem>> listPersonagens(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.listByIdCriador(id));
    }

    @GetMapping("/{id}/campanhas-mestradas")
    public ResponseEntity<List<Campanha>> listCampanhasMestradas(@PathVariable Long id) {
        return ResponseEntity.ok(campanhaService.listByIdMestre(id));
    }

    @GetMapping("/{id}/campanhas-participadas")
    public ResponseEntity<List<UsuarioCampanha>> listCampanhasParticipadas(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioCampanhaService.listByIdUsuario(id));
    }
}