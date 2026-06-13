package edu.ifal.virtable.domain.campanha;

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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "campanhas")
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario mestre;

    @ManyToOne
    @JoinColumn(name = "id_sistema_rpg", nullable = false)
    private SistemaRPG sistemaRPG;

    @NotNull
    @NotBlank
    private String nome;

    private String conteudo;

    public Campanha() {
    }

    public Campanha(Long id, Usuario mestre, SistemaRPG sistemaRPG, String nome, String conteudo) {
        this.id = id;
        this.mestre = mestre;
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
        return mestre;
    }

    public void setIdMestre(Usuario mestre) {
        this.mestre = mestre;
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
