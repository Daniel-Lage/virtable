package edu.ifal.virtable.domain.valor;

public class ValorInt extends ValorBase<Integer> {

    public ValorInt(int valor) {
        super(valor, TipoValor.Int);
    }
}