package edu.ifal.virtable.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifal.virtable.domain.sistema.SistemaRPG;
import edu.ifal.virtable.service.SistemaRPGService;

@RestController
@RequestMapping("/sistemas-rpg")
public class SistemaRPGController extends CrudController<SistemaRPG> {

    public SistemaRPGController(SistemaRPGService sistemaRPGService) {
        super(sistemaRPGService);
    }

}