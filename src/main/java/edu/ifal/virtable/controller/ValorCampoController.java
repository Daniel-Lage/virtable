package edu.ifal.virtable.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifal.virtable.model.ficha.ValorCampo;
import edu.ifal.virtable.service.ValorCampoService;

@RestController
@RequestMapping("/valores-campo")
public class ValorCampoController extends CrudController<ValorCampo> {

    public ValorCampoController(ValorCampoService valorCampoService) {
        super(valorCampoService);
    }

}