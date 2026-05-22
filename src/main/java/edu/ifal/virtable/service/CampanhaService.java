package edu.ifal.virtable.service;

import edu.ifal.virtable.domain.Campanha;

import java.util.ArrayList;
import java.util.List;

public class CampanhaService extends CrudService<Campanha> {

    public CampanhaService() {
        super(Campanha::getId);
    }

    public List<Campanha> buscarPorMestre(int idMestre) {
        List<Campanha> resultado = new ArrayList<>();

        for (Campanha campanha : listar()) {
            if (campanha.getIdMestre() == idMestre) {
                resultado.add(campanha);
            }
        }

        return resultado;
    }

    public List<Campanha> buscarPorSistema(int idSistemaRPG) {
        List<Campanha> resultado = new ArrayList<>();

        for (Campanha campanha : listar()) {
            if (campanha.getIdSistemaRPG() == idSistemaRPG) {
                resultado.add(campanha);
            }
        }

        return resultado;
    }

    public boolean atualizarNome(int idCampanha, String novoNome) {
        Campanha campanha = buscarPorId(idCampanha);

        if (campanha == null) {
            return false;
        }

        campanha.setNome(novoNome);
        return true;
    }

    public boolean usuarioEhMestre(int idUsuario, int idCampanha) {
        Campanha campanha = buscarPorId(idCampanha);

        if (campanha == null) {
            return false;
        }

        return campanha.getIdMestre() == idUsuario;
    }

    public boolean atualizarNomeComPermissao(
            int idUsuario,
            int idCampanha,
            String novoNome) {

        if (!usuarioEhMestre(idUsuario, idCampanha)) {
            return false;
        }

        return atualizarNome(idCampanha, novoNome);
    }

    public boolean removerComPermissao(int idUsuario, int idCampanha) {
        if (!usuarioEhMestre(idUsuario, idCampanha)) {
            return false;
        }

        return remover(idCampanha);
    }
}