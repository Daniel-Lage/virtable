package edu.ifal.virtable.domain.valor;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "opcoes_valores")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_valor", discriminatorType = DiscriminatorType.STRING)
public abstract class OpcoesValor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_campo_ficha", nullable = false)
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
