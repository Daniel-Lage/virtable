package edu.ifal.virtable.domain;

public abstract class ValorBase<T> implements Valor {

    private T valor;
    private TipoValor tipoValor;

    public ValorBase(T valor, TipoValor tipoValor) {
        this.valor = valor;
        this.tipoValor = tipoValor;
    }

    @Override
    public T getValor() {
        return valor;
    }

    @Override
    public TipoValor getTipoValor() {
        return tipoValor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }
}