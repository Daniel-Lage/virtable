package edu.ifal.virtable.domain.ficha;

import edu.ifal.virtable.domain.sistema.SistemaRPG;
import edu.ifal.virtable.domain.valor.TipoValor;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "campos_ficha")
public class CampoFicha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_sistema_rpg", nullable = false)
    private SistemaRPG sistemaRPG;

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

    public CampoFicha(Long id, SistemaRPG sistemaRPG, String nome, TipoCampo tipoCampo, TipoValor tipoValor) {
        this.id = id;
        this.sistemaRPG = sistemaRPG;
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

    public SistemaRPG getSistemaRPG() {
        return sistemaRPG;
    }

    public void setSistemaRPG(SistemaRPG sistemaRPG) {
        this.sistemaRPG = sistemaRPG;
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
