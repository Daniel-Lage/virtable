package edu.ifal.virtable.domain;

public class ValorInt implements Valor {
    private int valor;

    public ValorInt(int valor) {
        this.valor = valor;
    }

    @Override
    public Integer getValor() {
        return this.valor;
    }

    @Override
    public TipoValor getTipoValor() {
        return TipoValor.Int;
    }
}