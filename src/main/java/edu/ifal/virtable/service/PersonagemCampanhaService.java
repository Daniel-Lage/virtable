package edu.ifal.virtable.service;

import java.util.ArrayList;
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
        List<PersonagemCampanha> filtrados = new ArrayList<PersonagemCampanha>();

        for (PersonagemCampanha personagemCampanha : repository.findAll()) {
            if (personagemCampanha.getPersonagem().getId() == id) {

                filtrados.add(personagemCampanha);
            }
        }

        return filtrados;
    }

    public List<PersonagemCampanha> listByIdCampanha(Long id) {
        List<PersonagemCampanha> filtrados = new ArrayList<PersonagemCampanha>();

        for (PersonagemCampanha personagemCampanha : repository.findAll()) {
            if (personagemCampanha.getCampanha().getId() == id) {

                filtrados.add(personagemCampanha);
            }
        }

        return filtrados;
    }
}