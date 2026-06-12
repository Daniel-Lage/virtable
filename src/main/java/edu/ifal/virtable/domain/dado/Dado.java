package edu.ifal.virtable.domain.dado;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "dados")
public class Dado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long idSistemaRPG;

    @NotBlank
    private String nome;

    @NotNull
    private int limit;

    public Dado() {
    }

    public Dado(Long id, Long idSistemaRPG, String nome, int limit) {
        this.id = id;
        this.idSistemaRPG = idSistemaRPG;
        this.nome = nome;
        this.limit = limit;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSistemaRPG() {
        return this.idSistemaRPG;
    }

    public void setIdSistemaRPG(Long idSistemaRPG) {
        this.idSistemaRPG = idSistemaRPG;
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
