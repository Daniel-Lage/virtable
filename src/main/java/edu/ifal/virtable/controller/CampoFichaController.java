package edu.ifal.virtable.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifal.virtable.domain.ficha.CampoFicha;
import edu.ifal.virtable.service.CampoFichaService;

@RestController
@RequestMapping("/campos-ficha")
public class CampoFichaController extends CrudController<CampoFicha> {

    public CampoFichaController(CampoFichaService campoFichaService) {
        super(campoFichaService);
    }
}
