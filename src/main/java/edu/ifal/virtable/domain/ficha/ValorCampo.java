package edu.ifal.virtable.domain.ficha;

import edu.ifal.virtable.domain.valor.TipoValor;
import edu.ifal.virtable.domain.valor.Valor;

public class ValorCampo {
    private Long id;
    /// Associado a Personagem.id
    private Long idPersonagem;
    /// Associado a CampoFicha.id
    private Long idCampoFicha;

    /// Valor pode ser do tipo ValorInt ou ValorString
    private Valor valor;

    public ValorCampo(Long id, Long idPersonagem, Long idCampoFicha, Valor valor) {
        this.id = id;
        this.idPersonagem = idPersonagem;
        this.idCampoFicha = idCampoFicha;
        this.valor = valor;
    }

    public Long getId() {
        return this.id;
    }

    public Long getIdPersonagem() {
        return idPersonagem;
    }

    public Long getIdCampoFicha() {
        return idCampoFicha;
    }

    public Valor getValor() {
        return valor;
    }

    public void setValor(Valor valor) {
        this.valor = valor;
    }

    public void setIdPersonagem(Long idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    public void setIdCampoFicha(Long idCampoFicha) {
        this.idCampoFicha = idCampoFicha;
    }

    public TipoValor getTipoValor() {
        if (valor == null) {
            return null;
        }
        return valor.getTipoValor();
    }

}
