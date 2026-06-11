package edu.ifal.virtable.domain.personagem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "personagens")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Associado a SistemaRPG.id
    @NotNull(message = "O sistema é obrigatório")
    private Long idSistema;

    // Associado a Usuario.id
    @NotNull(message = "O usuário é obrigatório")
    private Long idUsuario;

    @NotBlank(message = "O nome do personagem é obrigatório")
    @Size(min = 2, max = 80, message = "O nome deve ter entre 2 e 80 caracteres")
    private String nome;

    public Personagem() {
    }

    public Personagem(Long id, Long idSistema, Long idUsuario, String nome) {
        this.id = id;
        this.idSistema = idSistema;
        this.idUsuario = idUsuario;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public Long getIdSistema() {
        return idSistema;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdSistema(Long idSistema) {
        this.idSistema = idSistema;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}