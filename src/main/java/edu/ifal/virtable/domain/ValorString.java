package edu.ifal.virtable.domain;

public class ValorString extends ValorBase<String> {

    public ValorString(String valor) {
        super(valor, TipoValor.String);
    }
}