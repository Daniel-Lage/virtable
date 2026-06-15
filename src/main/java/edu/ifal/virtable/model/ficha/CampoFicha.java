package edu.ifal.virtable.model.ficha;

import edu.ifal.virtable.helper.TipoCampo;
import edu.ifal.virtable.helper.TipoValor;
import edu.ifal.virtable.model.Item;
import edu.ifal.virtable.model.sistema.SistemaRPG;
import jakarta.persistence.Column;
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
@Table(name = "campo_ficha")
public class CampoFicha implements Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campo_ficha")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_sistema_rpg", nullable = false)
    private SistemaRPG sistemaRPG;

    @NotBlank(message = "Não deve estar em branco")
    @Column(name = "nome_campo_ficha")
    private String nome;

    @NotNull(message = "Não deve ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_campo_ficha")
    private TipoCampo tipoCampo;

    @NotNull(message = "Não deve ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_valor_campo_ficha")
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
