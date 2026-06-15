package edu.ifal.virtable.controller;

import edu.ifal.virtable.model.campanha.PersonagemCampanha;
import edu.ifal.virtable.service.PersonagemCampanhaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personagens-campanhas")
public class PersonagemCampanhaController extends CrudController<PersonagemCampanha> {

    public PersonagemCampanhaController(PersonagemCampanhaService personagemCampanhaService) {
        super(personagemCampanhaService);
    }

}