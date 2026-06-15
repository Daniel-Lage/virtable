package edu.ifal.virtable.model.campanha;

import edu.ifal.virtable.model.Item;
import edu.ifal.virtable.model.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_campanha")
public class UsuarioCampanha implements Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_campanha", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

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
