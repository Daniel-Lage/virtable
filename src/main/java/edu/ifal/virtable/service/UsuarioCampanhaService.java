package edu.ifal.virtable.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.model.campanha.UsuarioCampanha;
import edu.ifal.virtable.repository.UsuarioCampanhaRepository;

@Service
@Validated
public class UsuarioCampanhaService extends CrudService<UsuarioCampanha> {

    public UsuarioCampanhaService(UsuarioCampanhaRepository usuarioCampanhaRepository) {
        super(usuarioCampanhaRepository);
    }

    public List<UsuarioCampanha> listByIdUsuario(Long id) {
        List<UsuarioCampanha> todos = repository.findAll();

        List<UsuarioCampanha> filtrados = new ArrayList<UsuarioCampanha>();

        for (UsuarioCampanha campanha : todos) {
            if (campanha.getUsuario().getId() == id) {

                filtrados.add(campanha);
            }
        }

        return filtrados;
    }

    public List<UsuarioCampanha> listByIdCampanha(Long id) {
        List<UsuarioCampanha> todos = repository.findAll();

        List<UsuarioCampanha> filtrados = new ArrayList<UsuarioCampanha>();

        for (UsuarioCampanha campanha : todos) {
            if (campanha.getCampanha().getId() == id) {

                filtrados.add(campanha);
            }
        }

        return filtrados;
    }
}