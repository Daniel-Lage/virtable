package edu.ifal.virtable.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifal.virtable.model.dado.Dado;
import edu.ifal.virtable.model.dado.Modificador;
import edu.ifal.virtable.service.DadoService;
import edu.ifal.virtable.service.ModificadorService;

@RestController
@RequestMapping("/dados")
public class DadoController extends CrudController<Dado> {

    private final ModificadorService modificadorService;

    public DadoController(DadoService dadoService, ModificadorService modificadorService) {
        super(dadoService);
        this.modificadorService = modificadorService;
    }

    @GetMapping("/{id}/modificadores")
    public ResponseEntity<List<Modificador>> listModificadores(@PathVariable Long id) {
        return ResponseEntity.ok(modificadorService.listByIdDado(id));
    }
}
