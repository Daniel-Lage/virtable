package edu.ifal.virtable.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifal.virtable.model.dado.Modificador;
import edu.ifal.virtable.service.ModificadorService;

@RestController
@RequestMapping("/modificadores")
public class ModificadorController extends CrudController<Modificador> {

    public ModificadorController(ModificadorService modificadorService) {
        super(modificadorService);
    }
}
