package edu.ifal.virtable.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.domain.sistema.SistemaRPG;
import edu.ifal.virtable.repository.SistemaRPGRepository;

@Service
@Validated
public class SistemaRPGService extends CrudService<SistemaRPG> {

    public SistemaRPGService(SistemaRPGRepository sistemaRPGRepository) {
        super(sistemaRPGRepository);
    }
}