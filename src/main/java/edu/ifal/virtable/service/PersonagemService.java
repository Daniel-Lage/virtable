package edu.ifal.virtable.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.domain.personagem.Personagem;
import edu.ifal.virtable.repository.PersonagemRepository;

@Service
@Validated
public class PersonagemService extends CrudService<Personagem> {

    public PersonagemService(PersonagemRepository personagemRepository) {
        super(personagemRepository);
    }
}