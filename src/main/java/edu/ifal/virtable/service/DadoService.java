package edu.ifal.virtable.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.model.dado.Dado;
import edu.ifal.virtable.repository.DadoRepository;

@Service
@Validated
public class DadoService extends CrudService<Dado> {

    public DadoService(DadoRepository dadoRepository) {
        super(dadoRepository);
    }

    public List<Dado> listByIdSistemaRPG(Long id) {
        List<Dado> todos = repository.findAll();

        List<Dado> filtrados = List.of();

        for (Dado personagem : todos) {
            if (personagem.getSistemaRPG().getId() == id) {

                filtrados.add(personagem);
            }
        }

        return filtrados;
    }
}
