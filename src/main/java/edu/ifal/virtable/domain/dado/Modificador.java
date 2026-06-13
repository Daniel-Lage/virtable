package edu.ifal.virtable.domain.dado;

import edu.ifal.virtable.domain.ficha.ValorCampo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "modificadores")
public class Modificador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_valor_campo", nullable = false)
    private ValorCampo valorCampo;

    @ManyToOne
    @JoinColumn(name = "id_dado", nullable = false)
    private Dado dado;

    @NotNull
    private int multiplicador;

    public Modificador() {
    }

    public Modificador(Long id, ValorCampo valorCampo, Dado dado, int multiplicador) {
        this.id = id;
        this.valorCampo = valorCampo;
        this.dado = dado;
        this.multiplicador = multiplicador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ValorCampo getValorCampo() {
        return valorCampo;
    }

    public void setValorCampo(ValorCampo valorCampo) {
        this.valorCampo = valorCampo;
    }

    public Dado getDado() {
        return dado;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }

    public int getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(int multiplicador) {
        this.multiplicador = multiplicador;
    }
}
