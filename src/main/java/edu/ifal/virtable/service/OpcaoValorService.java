package edu.ifal.virtable.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.model.ficha.OpcaoValor;
import edu.ifal.virtable.repository.OpcaoValorRepository;

@Service
@Validated
public class OpcaoValorService extends CrudService<OpcaoValor> {

    public OpcaoValorService(
            OpcaoValorRepository opcaoValorRepository
    ) {
        super(opcaoValorRepository);
    }

    public List<OpcaoValor> listByIdCampoFicha(Long id) {
        List<OpcaoValor> filtrados = new ArrayList<>();

        for (OpcaoValor opcao : repository.findAll()) {
            if (
                    opcao.getCampoFicha() != null
                    && Objects.equals(
                            opcao.getCampoFicha().getId(),
                            id
                    )
            ) {
                filtrados.add(opcao);
            }
        }

        return filtrados;
    }
}