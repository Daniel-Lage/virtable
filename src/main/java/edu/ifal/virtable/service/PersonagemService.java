package edu.ifal.virtable.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.model.personagem.Personagem;
import edu.ifal.virtable.repository.PersonagemRepository;

@Service
@Validated
public class PersonagemService extends CrudService<Personagem> {

    public PersonagemService(PersonagemRepository personagemRepository) {
        super(personagemRepository);
    }

    public List<Personagem> listByIdCriador(Long id) {
        List<Personagem> todos = repository.findAll();

        List<Personagem> filtrados = new ArrayList<Personagem>();

        for (Personagem personagem : todos) {
            if (personagem.getCriador().getId() == id) {

                filtrados.add(personagem);
            }
        }

        return filtrados;
    }

    public List<Personagem> listByIdSistemaRPG(Long id) {
        List<Personagem> filtrados = new ArrayList<Personagem>();

        for (Personagem personagem : repository.findAll()) {
            if (personagem.getSistemaRPG().getId() == id) {

                filtrados.add(personagem);
            }
        }

        return filtrados;
    }

}