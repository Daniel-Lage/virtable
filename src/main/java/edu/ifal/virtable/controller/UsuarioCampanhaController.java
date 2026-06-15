package edu.ifal.virtable.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifal.virtable.model.campanha.UsuarioCampanha;
import edu.ifal.virtable.service.UsuarioCampanhaService;

@RestController
@RequestMapping("/usuarios-campanhas")
public class UsuarioCampanhaController extends CrudController<UsuarioCampanha> {

    public UsuarioCampanhaController(UsuarioCampanhaService usuarioCampanhaService) {
        super(usuarioCampanhaService);
    }

}