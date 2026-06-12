package edu.ifal.virtable.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.domain.ficha.CampoFicha;
import edu.ifal.virtable.repository.CampoFichaRepository;

@Service
@Validated
public class CampoFichaService extends CrudService<CampoFicha> {

    public CampoFichaService(CampoFichaRepository campoFichaRepository) {
        super(campoFichaRepository);
    }
}
