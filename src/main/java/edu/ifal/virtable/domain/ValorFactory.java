package edu.ifal.virtable.domain;

public class ValorFactory {

    public static Valor criarValor(TipoValor tipoValor, String valor) {

        if (tipoValor == TipoValor.Int) {
            return new ValorInt(Integer.parseInt(valor));
        }

        if (tipoValor == TipoValor.String) {
            return new ValorString(valor);
        }

        throw new IllegalArgumentException("Tipo de valor inválido.");
    }
}