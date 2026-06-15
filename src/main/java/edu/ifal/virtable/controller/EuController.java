package edu.ifal.virtable.controller;

import edu.ifal.virtable.dto.DeleteResponse;
import edu.ifal.virtable.model.campanha.Campanha;
import edu.ifal.virtable.model.campanha.UsuarioCampanha;
import edu.ifal.virtable.model.personagem.Personagem;
import edu.ifal.virtable.model.usuario.Usuario;
import edu.ifal.virtable.security.CustomUserDetails;
import edu.ifal.virtable.service.CampanhaService;
import edu.ifal.virtable.service.PersonagemService;
import edu.ifal.virtable.service.UsuarioCampanhaService;
import edu.ifal.virtable.service.UsuarioService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eu")
public class EuController {

    private final UsuarioService usuarioService;
    private final PersonagemService personagemService;
    private final CampanhaService campanhaService;
    private final UsuarioCampanhaService usuarioCampanhaService;

    public EuController(UsuarioService usuarioService, PersonagemService personagemService,
            CampanhaService campanhaService, UsuarioCampanhaService usuarioCampanhaService) {
        this.usuarioService = usuarioService;
        this.personagemService = personagemService;
        this.campanhaService = campanhaService;
        this.usuarioCampanhaService = usuarioCampanhaService;
    }

    @GetMapping
    public ResponseEntity<Usuario> read(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(usuarioService.read(userDetails.getId()));
    }

    @PutMapping
    public ResponseEntity<Usuario> update(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody @Valid Usuario request) {
        return ResponseEntity.ok(usuarioService.update(userDetails.getId(), request));
    }

    @DeleteMapping
    public ResponseEntity<DeleteResponse> delete(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(usuarioService.delete(userDetails.getId()));
    }

    @GetMapping("/personagens")
    public ResponseEntity<List<Personagem>> listPersonagens(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(personagemService.listByIdCriador(userDetails.getId()));
    }

    @GetMapping("/campanhas-mestradas")
    public ResponseEntity<List<Campanha>> listCampanhasMestradas(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(campanhaService.listByIdMestre(userDetails.getId()));
    }

    @GetMapping("/campanhas-participadas")
    public ResponseEntity<List<UsuarioCampanha>> listCampanhasParticipadas(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(usuarioCampanhaService.listByIdUsuario(userDetails.getId()));
    }
}