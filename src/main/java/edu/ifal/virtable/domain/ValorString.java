package edu.ifal.virtable.domain;

public class ValorString implements Valor {
    private String valor;

    public ValorString(String valor) {
        this.valor = valor;
    }

    @Override
    public String getValor() {
        return this.valor;
    }

    @Override
    public TipoValor getTipoValor() {
        return TipoValor.String;
    }
}