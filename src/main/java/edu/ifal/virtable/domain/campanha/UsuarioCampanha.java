package edu.ifal.virtable.domain.campanha;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "usuarios_campanhas")
public class UsuarioCampanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Associado a Usuario.id
    @NotNull(message = "O usuário é obrigatório")
    private Long idUsuario;

    // Associado a Campanha.id
    @NotNull(message = "A campanha é obrigatória")
    private Long idCampanha;

    public UsuarioCampanha() {
    }

    public UsuarioCampanha(Long id, Long idUsuario, Long idCampanha) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idCampanha = idCampanha;
    }

    public Long getId() {
        return id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Long getIdCampanha() {
        return idCampanha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdCampanha(Long idCampanha) {
        this.idCampanha = idCampanha;
    }
}