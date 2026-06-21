package edu.ifal.virtable.model.dado;

import edu.ifal.virtable.model.Item;
import edu.ifal.virtable.model.sistema.SistemaRPG;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "dado")
public class Dado implements Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dado", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_sistema_rpg", nullable = false)
    private SistemaRPG sistemaRPG;

    @NotBlank(message = "Não deve estar em branco")
    private String nome;

    @NotNull(message = "Não deve ser nulo")
    private Integer limite;

    public Dado() {
    }

    public Dado(Long id, SistemaRPG sistemaRPG, String nome, Integer limite) {
        this.id = id;
        this.sistemaRPG = sistemaRPG;
        this.nome = nome;
        this.limite = limite;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SistemaRPG getSistemaRPG() {
        return this.sistemaRPG;
    }

    public void setSistemaRPG(SistemaRPG sistemaRPG) {
        this.sistemaRPG = sistemaRPG;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getLimite() {
        return this.limite;
    }

    public void setLimite(Integer limite) {
        this.limite = limite;
    }
}
