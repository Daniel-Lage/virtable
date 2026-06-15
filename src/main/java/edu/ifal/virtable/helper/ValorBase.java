package edu.ifal.virtable.helper;

public abstract class ValorBase<T extends Object> implements Valor {

    private T valor;
    private TipoValor tipoValor;

    public ValorBase(T valor, TipoValor tipoValor) {
        this.valor = valor;
        this.tipoValor = tipoValor;
    }

    @Override
    public String getValor() {
        return valor.toString();
    }

    @Override
    public TipoValor getTipoValor() {
        return tipoValor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }
}