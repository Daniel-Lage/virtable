package edu.ifal.virtable.domain.valor;

public class ValorString extends ValorBase<String> {

    public ValorString(String valor) {
        super(valor, TipoValor.String);
    }
}