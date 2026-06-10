package edu.ifal.virtable.domain.factory;

import edu.ifal.virtable.domain.valor.Valor;
import edu.ifal.virtable.domain.valor.ValorInt;

public class ValorIntFactory extends ValorFactory {

    @Override
    public Valor criarValor(String valor) {
        return new ValorInt(Integer.parseInt(valor));
    }
}