package edu.ifal.virtable.service;

import edu.ifal.virtable.domain.Valor;
import edu.ifal.virtable.domain.ValorCampo;

import java.util.ArrayList;
import java.util.List;

public class ValorCampoServiceTests extends CrudService<ValorCampo> {

    public ValorCampoServiceTests() {
        super(ValorCampo::getId);
    }

    public List<ValorCampo> buscarPorPersonagem(int idPersonagem) {
        List<ValorCampo> resultado = new ArrayList<>();

        for (ValorCampo valorCampo : listar()) {
            if (valorCampo.getIdPersonagem() == idPersonagem) {
                resultado.add(valorCampo);
            }
        }

        return resultado;
    }

    public List<ValorCampo> buscarPorCampoFicha(int idCampoFicha) {
        List<ValorCampo> resultado = new ArrayList<>();

        for (ValorCampo valorCampo : listar()) {
            if (valorCampo.getIdCampoFicha() == idCampoFicha) {
                resultado.add(valorCampo);
            }
        }

        return resultado;
    }

    public ValorCampo buscarValorDoCampo(
            int idPersonagem,
            int idCampoFicha) {

        for (ValorCampo valorCampo : listar()) {
            if (valorCampo.getIdPersonagem() == idPersonagem
                    && valorCampo.getIdCampoFicha() == idCampoFicha) {

                return valorCampo;
            }
        }

        return null;
    }

    public boolean atualizarValor(
            int idPersonagem,
            int idCampoFicha,
            Valor novoValor) {

        ValorCampo valorCampo = buscarValorDoCampo(
                idPersonagem,
                idCampoFicha);

        if (valorCampo == null) {
            return false;
        }

        valorCampo.setValor(novoValor);
        return true;
    }

    public boolean removerValorDoCampo(
            int idPersonagem,
            int idCampoFicha) {

        ValorCampo valorCampo = buscarValorDoCampo(
                idPersonagem,
                idCampoFicha);

        if (valorCampo == null) {
            return false;
        }

        return remover(valorCampo.getId());
    }

    public boolean personagemPossuiCampo(
            int idPersonagem,
            int idCampoFicha) {

        return buscarValorDoCampo(idPersonagem, idCampoFicha) != null;
    }
}