package edu.ifal.virtable.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifal.virtable.model.dado.Modificador;
import edu.ifal.virtable.model.ficha.CampoFicha;
import edu.ifal.virtable.model.ficha.OpcaoValor;
import edu.ifal.virtable.model.ficha.ValorCampo;
import edu.ifal.virtable.service.CampoFichaService;
import edu.ifal.virtable.service.ModificadorService;
import edu.ifal.virtable.service.OpcaoValorService;
import edu.ifal.virtable.service.ValorCampoService;

@RestController
@RequestMapping("/campos-ficha")
public class CampoFichaController extends CrudController<CampoFicha> {
    private final ModificadorService modificadorService;
    private final OpcaoValorService opcaoValorService;
    private final ValorCampoService valorCampoService;

    public CampoFichaController(CampoFichaService campoFichaService, ValorCampoService valorCampoService,
            OpcaoValorService opcaoValorService, ModificadorService modificadorService) {
        super(campoFichaService);
        this.valorCampoService = valorCampoService;
        this.opcaoValorService = opcaoValorService;
        this.modificadorService = modificadorService;
    }

    @GetMapping("/{id}/valores-campo")
    public ResponseEntity<List<ValorCampo>> listValoresCampo(@PathVariable Long id) {
        return ResponseEntity.ok(valorCampoService.listByIdCampoFicha(id));
    }

    @GetMapping("/{id}/opcoes-valor")
    public ResponseEntity<List<OpcaoValor>> listOpcoesValor(@PathVariable Long id) {
        return ResponseEntity.ok(opcaoValorService.listByIdCampoFicha(id));
    }

    @GetMapping("/{id}/modificadores")
    public ResponseEntity<List<Modificador>> listModificadores(@PathVariable Long id) {
        return ResponseEntity.ok(modificadorService.listByIdCampoFicha(id));
    }
}
