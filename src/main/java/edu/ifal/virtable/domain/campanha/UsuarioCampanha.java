package edu.ifal.virtable.domain.campanha;

import edu.ifal.virtable.domain.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios_campanhas")
public class UsuarioCampanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Associado a Usuario.id
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Associado a Campanha.id
    @ManyToOne
    @JoinColumn(name = "id_campanha", nullable = false)
    private Campanha campanha;

    public UsuarioCampanha() {
    }

    public UsuarioCampanha(Long id, Usuario usuario, Campanha campanha) {
        this.id = id;
        this.usuario = usuario;
        this.campanha = campanha;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }
}
