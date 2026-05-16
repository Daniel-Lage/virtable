package edu.ifal.virtable.domain;

public class Modificador {
    int id;
    /// Associado a ValorCampo.id
    int idValorCampo;
    /// Associado a Dado.id
    int idDado;

    int multiplicador;

    public Modificador(int id, int idValorCampo, int idDado, int multiplicador) {
        this.id = id;
        this.idValorCampo = idValorCampo;
        this.idDado = idDado;
        this.multiplicador = multiplicador;
    }

    public int getId() {
        return id;
    }

    public int getIdValorCampo() {
        return idValorCampo;
    }

    public int getIdDado() {
        return idDado;
    }

    public int getMultiplicador() {
        return multiplicador;
    }
}
