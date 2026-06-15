package edu.ifal.virtable.model.campanha;

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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "campanha")
public class Campanha implements Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campanha", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @NotNull(message = "Não deve ser nulo")
    @ManyToOne
    @JoinColumn(name = "id_sistema_rpg", nullable = false)
    private SistemaRPG sistemaRPG;

    @NotNull(message = "Não deve ser nulo")
    @NotBlank(message = "Não deve estar em branco")
    @Column(name = "nome_campanha", nullable = false)
    private String nome;

    @Column(name = "txt_campanha", nullable = false)
    private String conteudo;

    public Campanha() {
    }

    public Campanha(Long id, Usuario usuario, SistemaRPG sistemaRPG, String nome, String conteudo) {
        this.id = id;
        this.usuario = usuario;
        this.sistemaRPG = sistemaRPG;
        this.nome = nome;
        this.conteudo = conteudo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getMestre() {
        return usuario;
    }

    public void setMestre(Usuario usuario) {
        this.usuario = usuario;
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

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
