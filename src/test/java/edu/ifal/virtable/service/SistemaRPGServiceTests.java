package edu.ifal.virtable.service;

import edu.ifal.virtable.domain.SistemaRPG;

public class SistemaRPGServiceTests extends CrudService<SistemaRPG> {

    public SistemaRPGServiceTests() {
        super(SistemaRPG::getId);
    }

    public SistemaRPG buscarPorNome(String nome) {
        for (SistemaRPG sistema : listar()) {
            if (sistema.getNome().equalsIgnoreCase(nome)) {
                return sistema;
            }
        }

        return null;
    }

    public boolean sistemaJaExiste(String nome) {
        return buscarPorNome(nome) != null;
    }

    public boolean atualizarNome(int idSistema, String novoNome) {
        SistemaRPG sistema = buscarPorId(idSistema);

        if (sistema == null) {
            return false;
        }

        if (sistemaJaExiste(novoNome)) {
            return false;
        }

        sistema.setNome(novoNome);
        return true;
    }

    public boolean atualizarDescricao(int idSistema, String novaDescricao) {
        SistemaRPG sistema = buscarPorId(idSistema);

        if (sistema == null) {
            return false;
        }

        sistema.setDescricao(novaDescricao);
        return true;
    }
}