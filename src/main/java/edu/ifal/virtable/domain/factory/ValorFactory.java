package edu.ifal.virtable.domain.factory;

import edu.ifal.virtable.domain.valor.Valor;

public abstract class ValorFactory {

    public abstract Valor criarValor(String valor);

}