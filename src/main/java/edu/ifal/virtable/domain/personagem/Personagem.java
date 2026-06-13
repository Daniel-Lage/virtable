package edu.ifal.virtable.domain.personagem;

import edu.ifal.virtable.domain.sistema.SistemaRPG;
import edu.ifal.virtable.domain.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "personagens")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Associado a SistemaRPG.id
    @ManyToOne
    @JoinColumn(name = "id_sistema_rpg", nullable = false)
    private SistemaRPG sistemaRPG;

    // Associado a Usuario.id
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @NotBlank(message = "O nome do personagem é obrigatório")
    @Size(min = 2, max = 80, message = "O nome deve ter entre 2 e 80 caracteres")
    private String nome;

    public Personagem() {
    }

    public Personagem(Long id, SistemaRPG sistemaRPG, Usuario usuario, String nome) {
        this.id = id;
        this.sistemaRPG = sistemaRPG;
        this.usuario = usuario;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public SistemaRPG getSistemaRPG() {
        return sistemaRPG;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSistemaRPG(SistemaRPG sistemaRPG) {
        this.sistemaRPG = sistemaRPG;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
