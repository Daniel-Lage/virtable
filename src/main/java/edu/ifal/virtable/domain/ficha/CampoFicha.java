package edu.ifal.virtable.domain.ficha;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import edu.ifal.virtable.domain.valor.TipoValor;

@Entity
@Table(name = "campos_ficha")
public class CampoFicha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long idSistemaRPG;

    @NotBlank
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoCampo tipoCampo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoValor tipoValor;

    public CampoFicha() {
    }

    public CampoFicha(Long id, Long idSistemaRPG, String nome, TipoCampo tipoCampo, TipoValor tipoValor) {
        this.id = id;
        this.idSistemaRPG = idSistemaRPG;
        this.nome = nome;
        this.tipoCampo = tipoCampo;
        this.tipoValor = tipoValor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSistemaRPG() {
        return idSistemaRPG;
    }

    public void setIdSistemaRPG(Long idSistemaRPG) {
        this.idSistemaRPG = idSistemaRPG;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoCampo getTipoCampo() {
        return tipoCampo;
    }

    public void setTipoCampo(TipoCampo tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    public TipoValor getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(TipoValor tipoValor) {
        this.tipoValor = tipoValor;
    }
}
