package edu.ifal.virtable.domain.valor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "opcoes_valores")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_valor", discriminatorType = DiscriminatorType.STRING)
public abstract class OpcoesValor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long idCampoFicha;

    @Transient
    private Valor valor;

    public OpcoesValor() {
    }

    public OpcoesValor(Long id, Long idCampoFicha) {
        this.id = id;
        this.idCampoFicha = idCampoFicha;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCampoFicha() {
        return idCampoFicha;
    }

    public void setIdCampoFicha(Long idCampoFicha) {
        this.idCampoFicha = idCampoFicha;
    }

    public Valor getValor() {
        return valor;
    }

    public void setValor(Valor valor) {
        this.valor = valor;
    }

    public TipoValor getTipoValor() {
        return valor != null ? valor.getTipoValor() : null;
    }

}
