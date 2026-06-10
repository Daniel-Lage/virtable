package edu.ifal.virtable.domain.factory;

import edu.ifal.virtable.domain.valor.Valor;
import edu.ifal.virtable.domain.valor.ValorString;

public class ValorStringFactory extends ValorFactory {

    @Override
    public Valor criarValor(String valor) {
        return new ValorString(valor);
    }
}