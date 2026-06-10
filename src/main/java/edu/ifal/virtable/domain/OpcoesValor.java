package edu.ifal.virtable.domain;

public abstract class OpcoesValor {
    private Long id;
    /// Associado a CampoFicha.id
    private Long idCampoFicha;

    /// Valor pode ser String ou int
    private Valor valor;

    public OpcoesValor(Long id, Long idCampoFicha) {
        this.id = id;
        this.idCampoFicha = idCampoFicha;
    }

    public Long getId() {
        return this.id;
    }

    public Long getIdCampoFicha() {
        return idCampoFicha;
    }

    public Valor getValor() {
        return valor;
    }

    public TipoValor getTipoValor() {
        return null;
    }

}
