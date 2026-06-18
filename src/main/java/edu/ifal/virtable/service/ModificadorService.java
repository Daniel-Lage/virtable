package edu.ifal.virtable.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.model.dado.Modificador;
import edu.ifal.virtable.repository.ModificadorRepository;

@Service
@Validated
public class ModificadorService extends CrudService<Modificador> {

    public ModificadorService(
            ModificadorRepository modificadorRepository
    ) {
        super(modificadorRepository);
    }

    public List<Modificador> listByIdDado(Long id) {
        List<Modificador> filtrados = new ArrayList<>();

        for (Modificador modificador : repository.findAll()) {
            if (
                    modificador.getDado() != null
                    && Objects.equals(
                            modificador.getDado().getId(),
                            id
                    )
            ) {
                filtrados.add(modificador);
            }
        }

        return filtrados;
    }

    public List<Modificador> listByIdCampoFicha(Long id) {
        List<Modificador> filtrados = new ArrayList<>();

        for (Modificador modificador : repository.findAll()) {
            if (
                    modificador.getCampoFicha() != null
                    && Objects.equals(
                            modificador.getCampoFicha().getId(),
                            id
                    )
            ) {
                filtrados.add(modificador);
            }
        }

        return filtrados;
    }
}