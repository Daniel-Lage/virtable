package edu.ifal.virtable.model.personagem;

import edu.ifal.virtable.model.Item;
import edu.ifal.virtable.model.sistema.SistemaRPG;
import edu.ifal.virtable.model.usuario.Usuario;
import jakarta.persistence.Column;
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
@Table(name = "personagem")
public class Personagem implements Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personagem", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_sistema_rpg", nullable = false)
    private SistemaRPG sistemaRPG;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario criador;

    @NotBlank(message = "O nome do personagem é obrigatório")
    @Size(min = 2, max = 80, message = "O nome deve ter entre 2 e 80 caracteres")
    @Column(name = "nome_personagem", nullable = false)
    private String nome;

    public Personagem() {
    }

    public Personagem(Long id, SistemaRPG sistemaRPG, Usuario criador, String nome) {
        this.id = id;
        this.sistemaRPG = sistemaRPG;
        this.criador = criador;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public SistemaRPG getSistemaRPG() {
        return sistemaRPG;
    }

    public Usuario getCriador() {
        return criador;
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

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
