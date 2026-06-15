package edu.ifal.virtable.helper;

public abstract class ValorFactory<T> {

    public abstract ValorBase<T> criarValor(String valor);

}