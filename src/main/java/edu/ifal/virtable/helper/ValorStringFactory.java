package edu.ifal.virtable.helper;

public class ValorStringFactory extends ValorFactory<String> {

    @Override
    public ValorBase<String> criarValor(String valor) {
        return new ValorString(valor);
    }
}