package edu.ifal.virtable.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.domain.campanha.UsuarioCampanha;
import edu.ifal.virtable.repository.UsuarioCampanhaRepository;

@Service
@Validated
public class UsuarioCampanhaService extends CrudService<UsuarioCampanha> {

    public UsuarioCampanhaService(UsuarioCampanhaRepository usuarioCampanhaRepository) {
        super(usuarioCampanhaRepository);
    }
}