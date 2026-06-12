package edu.ifal.virtable.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.domain.dado.Dado;
import edu.ifal.virtable.repository.DadoRepository;

@Service
@Validated
public class DadoService extends CrudService<Dado> {

    public DadoService(DadoRepository dadoRepository) {
        super(dadoRepository);
    }
}
