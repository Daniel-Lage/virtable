package edu.ifal.virtable.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CrudService<T> {

    private List<T> dados = new ArrayList<>();
    private Function<T, Integer> getId;

    public CrudService(Function<T, Integer> getId) {
        this.getId = getId;
    }

    public void criar(T objeto) {
        dados.add(objeto);
    }

    public List<T> listar() {
        return dados;
    }

    public T buscarPorId(int id) {
        for (T objeto : dados) {
            if (getId.apply(objeto) == id) {
                return objeto;
            }
        }

        return null;
    }

    public boolean atualizar(int id, T novoObjeto) {
        for (int i = 0; i < dados.size(); i++) {
            if (getId.apply(dados.get(i)) == id) {
                dados.set(i, novoObjeto);
                return true;
            }
        }

        return false;
    }

    public boolean remover(int id) {
        return dados.removeIf(objeto -> getId.apply(objeto) == id);
    }
}