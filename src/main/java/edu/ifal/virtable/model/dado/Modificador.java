package edu.ifal.virtable.model.dado;

import edu.ifal.virtable.model.Item;
import edu.ifal.virtable.model.ficha.CampoFicha;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "modificador")
public class Modificador implements Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modificador", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_campo_ficha", nullable = false)
    private CampoFicha campoFicha;

    @ManyToOne
    @JoinColumn(name = "id_dado", nullable = false)
    private Dado dado;

    @NotNull(message = "Não deve ser nulo")
    @Column(name = "vlr_multiplicador")
    private Integer multiplicador;

    public Modificador() {
    }

    public Modificador(Long id, CampoFicha campoFicha, Dado dado, Integer multiplicador) {
        this.id = id;
        this.campoFicha = campoFicha;
        this.dado = dado;
        this.multiplicador = multiplicador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CampoFicha getCampoFicha() {
        return campoFicha;
    }

    public void setCampoFicha(CampoFicha campoFicha) {
        this.campoFicha = campoFicha;
    }

    public Dado getDado() {
        return dado;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }

    public Integer getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(Integer multiplicador) {
        this.multiplicador = multiplicador;
    }
}
