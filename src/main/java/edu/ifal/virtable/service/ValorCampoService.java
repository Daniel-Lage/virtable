package edu.ifal.virtable.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.model.ficha.ValorCampo;
import edu.ifal.virtable.repository.ValorCampoRepository;

@Service
@Validated
public class ValorCampoService extends CrudService<ValorCampo> {

    public ValorCampoService(
            ValorCampoRepository valorCampoRepository) {
        super(valorCampoRepository);
    }

    public List<ValorCampo> listByIdCampoFicha(Long id) {
        List<ValorCampo> filtrados = new ArrayList<ValorCampo>();

        for (ValorCampo personagem : repository.findAll()) {
            if (personagem.getCampoFicha().getId() == id) {

                filtrados.add(personagem);
            }
        }

        return filtrados;
    }

    public List<ValorCampo> listByIdPersonagem(Long id) {
        List<ValorCampo> filtrados = new ArrayList<ValorCampo>();

        for (ValorCampo valorCampo : repository.findAll()) {
            if (valorCampo.getPersonagem().getId() == id) {

                filtrados.add(valorCampo);
            }
        }

        return filtrados;
    }
}