package edu.ifal.virtable.service;

import java.util.ArrayList;
import java.util.List;

import edu.ifal.virtable.domain.Personagem;

public class PersonagemService extends CrudService<Personagem> {

    public PersonagemService() {
        super(Personagem::getId);
    }

    public List<Personagem> buscarPorUsuario(int idUsuario) {
        List<Personagem> resultado = new ArrayList<>();

        for (Personagem personagem : listar()) {
            if (personagem.getIdUsuario() == idUsuario) {
                resultado.add(personagem);
            }
        }

        return resultado;
    }

    public List<Personagem> buscarPorSistema(int idSistema) {
        List<Personagem> resultado = new ArrayList<>();

        for (Personagem personagem : listar()) {
            if (personagem.getIdSistema() == idSistema) {
                resultado.add(personagem);
            }
        }

        return resultado;
    }

    public boolean atualizarNome(int idPersonagem, String novoNome) {
        Personagem personagem = buscarPorId(idPersonagem);

        if (personagem == null) {
            return false;
        }

        personagem.setNome(novoNome);
        return true;
    }

    public boolean usuarioTemPermissao(int idUsuario, int idPersonagem) {
        Personagem personagem = buscarPorId(idPersonagem);

        if (personagem == null) {
            return false;
        }

        return personagem.getIdUsuario() == idUsuario;
    }

    public boolean atualizarNomeComPermissao(
            int idUsuario,
            int idPersonagem,
            String novoNome) {

        if (!usuarioTemPermissao(idUsuario, idPersonagem)) {
            return false;
        }

        return atualizarNome(idPersonagem, novoNome);
    }

    public boolean removerComPermissao(
            int idUsuario,
            int idPersonagem) {

        if (!usuarioTemPermissao(idUsuario, idPersonagem)) {
            return false;
        }

        return remover(idPersonagem);
    }
}