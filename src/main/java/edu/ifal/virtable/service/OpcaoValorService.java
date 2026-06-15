package edu.ifal.virtable.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.model.ficha.OpcaoValor;
import edu.ifal.virtable.repository.OpcaoValorRepository;

@Service
@Validated
public class OpcaoValorService extends CrudService<OpcaoValor> {

    public OpcaoValorService(OpcaoValorRepository opcoesValorRepository) {
        super(opcoesValorRepository);
    }

    public List<OpcaoValor> listByIdCampoFicha(Long id) {
        List<OpcaoValor> todos = repository.findAll();

        List<OpcaoValor> filtrados = List.of();

        for (OpcaoValor personagem : todos) {
            if (personagem.getCampoFicha().getId() == id) {

                filtrados.add(personagem);
            }
        }

        return filtrados;
    }

}
