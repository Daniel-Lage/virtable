package edu.ifal.virtable.domain;

public class ValorIntFactory extends ValorFactory {

    @Override
    public Valor criarValor(String valor) {
        return new ValorInt(Integer.parseInt(valor));
    }
}