package edu.ifal.virtable.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.model.dado.Modificador;
import edu.ifal.virtable.repository.ModificadorRepository;

@Service
@Validated
public class ModificadorService extends CrudService<Modificador> {

    public ModificadorService(ModificadorRepository modificadorRepository) {
        super(modificadorRepository);
    }

    public List<Modificador> listByIdDado(Long id) {
        List<Modificador> todos = repository.findAll();

        List<Modificador> filtrados = new ArrayList<Modificador>();

        for (Modificador personagem : todos) {
            if (personagem.getDado().getId() == id) {

                filtrados.add(personagem);
            }
        }

        return filtrados;
    }

    public List<Modificador> listByIdCampoFicha(Long id) {
        List<Modificador> todos = repository.findAll();

        List<Modificador> filtrados = new ArrayList<Modificador>();

        for (Modificador personagem : todos) {
            if (personagem.getCampoFicha().getId() == id) {

                filtrados.add(personagem);
            }
        }

        return filtrados;
    }
}
