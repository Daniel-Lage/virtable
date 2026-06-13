package edu.ifal.virtable.domain.dado;

import edu.ifal.virtable.domain.sistema.SistemaRPG;
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
@Table(name = "dados")
public class Dado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_sistema_rpg", nullable = false)
    private SistemaRPG sistemaRPG;

    @NotBlank
    private String nome;

    @NotNull
    private int limit;

    public Dado() {
    }

    public Dado(Long id, SistemaRPG sistemaRPG, String nome, int limit) {
        this.id = id;
        this.sistemaRPG = sistemaRPG;
        this.nome = nome;
        this.limit = limit;
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

    public String getName() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
