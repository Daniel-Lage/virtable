package edu.ifal.virtable.helper;

public class ValorIntFactory extends ValorFactory<Integer> {

    @Override
    public ValorBase<Integer> criarValor(String valor) {
        return new ValorInt(Integer.parseInt(valor));
    }
}