package edu.ifal.virtable.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifal.virtable.domain.campanha.Campanha;
import edu.ifal.virtable.service.CampanhaService;

@RestController
@RequestMapping("/campanhas")
public class CampanhaController extends CrudController<Campanha> {

    public CampanhaController(CampanhaService campanhaService) {
        super(campanhaService);
    }
}
