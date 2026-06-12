package edu.ifal.virtable.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.domain.dado.Modificador;
import edu.ifal.virtable.repository.ModificadorRepository;

@Service
@Validated
public class ModificadorService extends CrudService<Modificador> {

    public ModificadorService(ModificadorRepository modificadorRepository) {
        super(modificadorRepository);
    }
}
