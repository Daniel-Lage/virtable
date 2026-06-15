package edu.ifal.virtable.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifal.virtable.model.ficha.OpcaoValor;
import edu.ifal.virtable.service.OpcaoValorService;

@RestController
@RequestMapping("/opcoes-valor")
public class OpcaoValorController extends CrudController<OpcaoValor> {

    public OpcaoValorController(OpcaoValorService opcoesValorService) {
        super(opcoesValorService);
    }
}
