package edu.ifal.virtable.domain.dado;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "modificadores")
public class Modificador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long idValorCampo;

    @NotNull
    private Long idDado;

    @NotNull
    private int multiplicador;

    public Modificador() {
    }

    public Modificador(Long id, Long idValorCampo, Long idDado, int multiplicador) {
        this.id = id;
        this.idValorCampo = idValorCampo;
        this.idDado = idDado;
        this.multiplicador = multiplicador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdValorCampo() {
        return idValorCampo;
    }

    public void setIdValorCampo(Long idValorCampo) {
        this.idValorCampo = idValorCampo;
    }

    public Long getIdDado() {
        return idDado;
    }

    public void setIdDado(Long idDado) {
        this.idDado = idDado;
    }

    public int getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(int multiplicador) {
        this.multiplicador = multiplicador;
    }
}
