package edu.ifal.virtable.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifal.virtable.domain.personagem.Personagem;
import edu.ifal.virtable.service.PersonagemService;

@RestController
@RequestMapping("/personagens")
public class PersonagemController extends CrudController<Personagem> {

    public PersonagemController(PersonagemService personagemService) {
        super(personagemService);
    }

}