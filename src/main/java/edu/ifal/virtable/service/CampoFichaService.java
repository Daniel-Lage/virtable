package edu.ifal.virtable.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.model.ficha.CampoFicha;
import edu.ifal.virtable.repository.CampoFichaRepository;

@Service
@Validated
public class CampoFichaService extends CrudService<CampoFicha> {

    public CampoFichaService(
            CampoFichaRepository campoFichaRepository) {
        super(campoFichaRepository);
    }

    public List<CampoFicha> listByIdSistemaRPG(Long id) {
        List<CampoFicha> todos = repository.findAll();

        List<CampoFicha> filtrados = new ArrayList<CampoFicha>();

        for (CampoFicha personagem : todos) {
            if (personagem.getSistemaRPG().getId() == id) {

                filtrados.add(personagem);
            }
        }

        return filtrados;
    }
}