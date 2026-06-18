package edu.ifal.virtable.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.model.campanha.Campanha;
import edu.ifal.virtable.repository.CampanhaRepository;

@Service
@Validated
public class CampanhaService extends CrudService<Campanha> {

    public CampanhaService(CampanhaRepository campanhaRepository) {
        super(campanhaRepository);
    }

    public List<Campanha> listByIdMestre(Long id) {
        List<Campanha> todos = repository.findAll();

        List<Campanha> filtrados = new ArrayList<Campanha>();

        for (Campanha campanha : todos) {
            if (campanha.getMestre().getId() == id) {

                filtrados.add(campanha);
            }
        }

        return filtrados;
    }

    public List<Campanha> listByIdSistemaRPG(Long id) {
        List<Campanha> todos = repository.findAll();

        List<Campanha> filtrados = new ArrayList<Campanha>();

        for (Campanha campanha : todos) {
            if (campanha.getSistemaRPG().getId() == id) {

                filtrados.add(campanha);
            }
        }

        return filtrados;
    }
}
