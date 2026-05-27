package edu.ifal.virtable.service;

import edu.ifal.virtable.domain.CampoFicha;
import edu.ifal.virtable.domain.TipoCampo;
import edu.ifal.virtable.domain.TipoValor;

import java.util.ArrayList;
import java.util.List;

public class CampoFichaServiceTests extends CrudServiceTests<CampoFicha> {

    public CampoFichaServiceTests() {
        super(CampoFicha::getId);
    }

    public List<CampoFicha> buscarPorSistemaRPG(int idSistemaRPG) {
        List<CampoFicha> camposEncontrados = new ArrayList<>();

        for (CampoFicha campo : listar()) {
            if (campo.getIdSistemaRPG() == idSistemaRPG) {
                camposEncontrados.add(campo);
            }
        }

        return camposEncontrados;
    }

    public CampoFicha buscarPorNomeNoSistema(
            int idSistemaRPG,
            String nome) {

        for (CampoFicha campo : listar()) {
            if (campo.getIdSistemaRPG() == idSistemaRPG
                    && campo.getNome().equalsIgnoreCase(nome)) {
                return campo;
            }
        }

        return null;
    }

    public boolean campoJaExisteNoSistema(
            int idSistemaRPG,
            String nome) {

        return buscarPorNomeNoSistema(idSistemaRPG, nome) != null;
    }

    public boolean atualizarNome(
            int idCampoFicha,
            String novoNome) {

        CampoFicha campo = buscarPorId(idCampoFicha);

        if (campo == null) {
            return false;
        }

        if (campoJaExisteNoSistema(campo.getIdSistemaRPG(), novoNome)) {
            return false;
        }

        campo.setNome(novoNome);
        return true;
    }

    public boolean atualizarTipoCampo(
            int idCampoFicha,
            TipoCampo novoTipoCampo) {

        CampoFicha campo = buscarPorId(idCampoFicha);

        if (campo == null) {
            return false;
        }

        campo.setTipoCampo(novoTipoCampo);
        return true;
    }

    public boolean atualizarTipoValor(
            int idCampoFicha,
            TipoValor novoTipoValor) {

        CampoFicha campo = buscarPorId(idCampoFicha);

        if (campo == null) {
            return false;
        }

        campo.setTipoValor(novoTipoValor);
        return true;
    }

    public boolean atualizarSistemaRPG(
            int idCampoFicha,
            int novoIdSistemaRPG) {

        CampoFicha campo = buscarPorId(idCampoFicha);

        if (campo == null) {
            return false;
        }

        campo.setIdSistemaRPG(novoIdSistemaRPG);
        return true;
    }
}