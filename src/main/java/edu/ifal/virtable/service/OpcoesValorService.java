package edu.ifal.virtable.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.domain.valor.OpcoesValor;
import edu.ifal.virtable.repository.OpcoesValorRepository;

@Service
@Validated
public class OpcoesValorService extends CrudService<OpcoesValor> {

    public OpcoesValorService(OpcoesValorRepository opcoesValorRepository) {
        super(opcoesValorRepository);
    }
}
