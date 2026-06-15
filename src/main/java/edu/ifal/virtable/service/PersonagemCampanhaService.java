package edu.ifal.virtable.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.model.campanha.PersonagemCampanha;
import edu.ifal.virtable.repository.PersonagemCampanhaRepository;

@Service
@Validated
public class PersonagemCampanhaService extends CrudService<PersonagemCampanha> {

    public PersonagemCampanhaService(PersonagemCampanhaRepository personagemCampanhaRepository) {
        super(personagemCampanhaRepository);
    }

    public List<PersonagemCampanha> listByIdPersonagem(Long id) {
        List<PersonagemCampanha> todos = repository.findAll();

        List<PersonagemCampanha> filtrados = List.of();

        for (PersonagemCampanha campanha : todos) {
            if (campanha.getPersonagem().getId() == id) {

                filtrados.add(campanha);
            }
        }

        return filtrados;
    }

    public List<PersonagemCampanha> listByIdCampanha(Long id) {
        List<PersonagemCampanha> todos = repository.findAll();

        List<PersonagemCampanha> filtrados = List.of();

        for (PersonagemCampanha campanha : todos) {
            if (campanha.getCampanha().getId() == id) {

                filtrados.add(campanha);
            }
        }

        return filtrados;
    }
}