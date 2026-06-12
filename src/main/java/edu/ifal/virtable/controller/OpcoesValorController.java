package edu.ifal.virtable.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifal.virtable.domain.valor.OpcoesValor;
import edu.ifal.virtable.service.OpcoesValorService;

@RestController
@RequestMapping("/opcoes-valores")
public class OpcoesValorController extends CrudController<OpcoesValor> {

    public OpcoesValorController(OpcoesValorService opcoesValorService) {
        super(opcoesValorService);
    }
}
