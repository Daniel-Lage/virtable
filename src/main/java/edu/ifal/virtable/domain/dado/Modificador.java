package edu.ifal.virtable.domain.dado;

public class Modificador {
    Long id;
    /// Associado a ValorCampo.id
    Long idValorCampo;
    /// Associado a Dado.id
    Long idDado;

    int multiplicador;

    public Modificador(Long id, Long idValorCampo, Long idDado, int multiplicador) {
        this.id = id;
        this.idValorCampo = idValorCampo;
        this.idDado = idDado;
        this.multiplicador = multiplicador;
    }

    public Long getId() {
        return id;
    }

    public Long getIdValorCampo() {
        return idValorCampo;
    }

    public Long getIdDado() {
        return idDado;
    }

    public int getMultiplicador() {
        return multiplicador;
    }
}
