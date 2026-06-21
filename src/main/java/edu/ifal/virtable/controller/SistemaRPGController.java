package edu.ifal.virtable.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifal.virtable.model.campanha.Campanha;
import edu.ifal.virtable.model.dado.Dado;
import edu.ifal.virtable.model.ficha.CampoFicha;
import edu.ifal.virtable.model.personagem.Personagem;
import edu.ifal.virtable.model.sistema.SistemaRPG;
import edu.ifal.virtable.service.CampanhaService;
import edu.ifal.virtable.service.CampoFichaService;
import edu.ifal.virtable.service.DadoService;
import edu.ifal.virtable.service.PersonagemService;
import edu.ifal.virtable.service.SistemaRPGService;

@RestController
@RequestMapping("/sistemas-rpg")
public class SistemaRPGController extends CrudController<SistemaRPG> {

    private final DadoService dadoService;
    private final CampoFichaService campoFichaService;
    private final CampanhaService campanhaService;
    private final PersonagemService personagemService;

    public SistemaRPGController(SistemaRPGService sistemaRPGService, CampanhaService campanhaService,
            CampoFichaService campoFichaService, DadoService dadoService, PersonagemService personagemService) {
        super(sistemaRPGService);
        this.campanhaService = campanhaService;
        this.campoFichaService = campoFichaService;
        this.dadoService = dadoService;
        this.personagemService = personagemService;
    }

    @GetMapping("/{id}/personagens")
    public ResponseEntity<List<Personagem>> listPersonagens(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.listByIdSistemaRPG(id));
    }

    @GetMapping("/{id}/campanhas")
    public ResponseEntity<List<Campanha>> listCampanhas(@PathVariable Long id) {
        return ResponseEntity.ok(campanhaService.listByIdSistemaRPG(id));
    }

    @GetMapping("/{id}/campos-ficha")
    public ResponseEntity<List<CampoFicha>> listCamposFicha(@PathVariable Long id) {
        return ResponseEntity.ok(campoFichaService.listByIdSistemaRPG(id));
    }

    @GetMapping("/{id}/dados")
    public ResponseEntity<List<Dado>> listDados(@PathVariable Long id) {
        return ResponseEntity.ok(dadoService.listByIdSistemaRPG(id));
    }
}
