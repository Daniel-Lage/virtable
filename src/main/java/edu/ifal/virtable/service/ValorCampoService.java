package edu.ifal.virtable.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.model.ficha.ValorCampo;
import edu.ifal.virtable.repository.ValorCampoRepository;

@Service
@Validated
public class ValorCampoService extends CrudService<ValorCampo> {

    public ValorCampoService(ValorCampoRepository valorCampoRepository) {
        super(valorCampoRepository);
    }

    public List<ValorCampo> listByIdCampoFicha(Long id) {
        List<ValorCampo> todos = repository.findAll();

        List<ValorCampo> filtrados = List.of();

        for (ValorCampo personagem : todos) {
            if (personagem.getCampoFicha().getId() == id) {

                filtrados.add(personagem);
            }
        }

        return filtrados;
    }

    public List<ValorCampo> listByIdPersonagem(Long id) {
        List<ValorCampo> todos = repository.findAll();

        List<ValorCampo> filtrados = List.of();

        for (ValorCampo personagem : todos) {
            if (personagem.getPersonagem().getId() == id) {

                filtrados.add(personagem);
            }
        }

        return filtrados;
    }
}