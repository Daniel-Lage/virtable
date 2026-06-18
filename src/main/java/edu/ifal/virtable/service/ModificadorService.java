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

    public ModificadorService(
            ModificadorRepository modificadorRepository) {
        super(modificadorRepository);
    }

    public List<Modificador> listByIdDado(Long id) {
        List<Modificador> filtrados = new ArrayList<Modificador>();

        for (Modificador modificador : repository.findAll()) {
            if (modificador.getDado().getId() == id) {

                filtrados.add(modificador);
            }
        }

        return filtrados;
    }

    public List<Modificador> listByIdCampoFicha(Long id) {
        List<Modificador> filtrados = new ArrayList<Modificador>();

        for (Modificador modificador : repository.findAll()) {
            if (modificador.getCampoFicha().getId() == id) {

                filtrados.add(modificador);
            }
        }

        return filtrados;
    }
}