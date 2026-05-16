package edu.ifal.virtable.domain;

public abstract class OpcoesValor {
    private int id;
    /// Associado a CampoFicha.id
    private int idCampoFicha;

    /// Valor pode ser String ou int
    private Valor valor;

    public OpcoesValor(int id, int idCampoFicha) {
        this.id = id;
        this.idCampoFicha = idCampoFicha;
    }

    public int getId() {
        return this.id;
    }

    public int getIdCampoFicha() {
        return idCampoFicha;
    }

    public Valor getValor() {
        return valor;
    }

    public TipoValor getTipoValor() {
        return null;
    }

}
