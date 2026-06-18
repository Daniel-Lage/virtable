package edu.ifal.virtable.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import edu.ifal.virtable.model.ficha.ValorCampo;
import edu.ifal.virtable.repository.ValorCampoRepository;

@Service
@Validated
public class ValorCampoService extends CrudService<ValorCampo> {

    public ValorCampoService(
            ValorCampoRepository valorCampoRepository
    ) {
        super(valorCampoRepository);
    }

    public List<ValorCampo> listByIdCampoFicha(Long id) {
        List<ValorCampo> filtrados = new ArrayList<>();

        for (ValorCampo valorCampo : repository.findAll()) {
            if (
                    valorCampo.getCampoFicha() != null
                    && Objects.equals(
                            valorCampo.getCampoFicha().getId(),
                            id
                    )
            ) {
                filtrados.add(valorCampo);
            }
        }

        return filtrados;
    }

    public List<ValorCampo> listByIdPersonagem(Long id) {
        List<ValorCampo> filtrados = new ArrayList<>();

        for (ValorCampo valorCampo : repository.findAll()) {
            if (
                    valorCampo.getPersonagem() != null
                    && Objects.equals(
                            valorCampo.getPersonagem().getId(),
                            id
                    )
            ) {
                filtrados.add(valorCampo);
            }
        }

        return filtrados;
    }
}