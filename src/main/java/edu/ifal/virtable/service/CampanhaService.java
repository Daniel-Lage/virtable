package edu.ifal.virtable.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.domain.campanha.Campanha;
import edu.ifal.virtable.repository.CampanhaRepository;

@Service
@Validated
public class CampanhaService extends CrudService<Campanha> {

    public CampanhaService(CampanhaRepository campanhaRepository) {
        super(campanhaRepository);
    }
}
