package edu.ifal.virtable.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifal.virtable.domain.dado.Dado;
import edu.ifal.virtable.service.DadoService;

@RestController
@RequestMapping("/dados")
public class DadoController extends CrudController<Dado> {

    public DadoController(DadoService dadoService) {
        super(dadoService);
    }
}
