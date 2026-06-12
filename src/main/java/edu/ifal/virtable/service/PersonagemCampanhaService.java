package edu.ifal.virtable.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.domain.campanha.PersonagemCampanha;
import edu.ifal.virtable.repository.PersonagemCampanhaRepository;

@Service
@Validated
public class PersonagemCampanhaService extends CrudService<PersonagemCampanha> {

    public PersonagemCampanhaService(PersonagemCampanhaRepository personagemCampanhaRepository) {
        super(personagemCampanhaRepository);
    }
}