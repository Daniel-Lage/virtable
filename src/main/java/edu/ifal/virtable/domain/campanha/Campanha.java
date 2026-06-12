package edu.ifal.virtable.domain.campanha;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import edu.ifal.virtable.domain.sistema.SistemaRPG;
import edu.ifal.virtable.domain.usuario.Usuario;

@Entity
@Table(name = "campanhas")
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long idMestre;

    @NotNull
    private Long idSistemaRPG;

    @NotBlank
    private String nome;

    public Campanha() {
    }

    public Campanha(Long id, Long idMestre, Long idSistemaRPG, String nome, Usuario mestre, SistemaRPG sistemaRPG,
            Usuario[] jogadores) {
        this.id = id;
        this.idMestre = idMestre;
        this.idSistemaRPG = idSistemaRPG;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMestre() {
        return idMestre;
    }

    public void setIdMestre(Long idMestre) {
        this.idMestre = idMestre;
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
}
