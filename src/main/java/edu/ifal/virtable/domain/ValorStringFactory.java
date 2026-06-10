package edu.ifal.virtable.domain;

public class ValorStringFactory extends ValorFactory {

    @Override
    public Valor criarValor(String valor) {
        return new ValorString(valor);
    }
}