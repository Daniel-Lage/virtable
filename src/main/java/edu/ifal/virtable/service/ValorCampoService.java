package edu.ifal.virtable.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.domain.ficha.ValorCampo;
import edu.ifal.virtable.repository.ValorCampoRepository;

@Service
@Validated
public class ValorCampoService extends CrudService<ValorCampo> {

    public ValorCampoService(ValorCampoRepository valorCampoRepository) {
        super(valorCampoRepository);
    }
}