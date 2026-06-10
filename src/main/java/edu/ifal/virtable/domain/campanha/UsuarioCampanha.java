package edu.ifal.virtable.domain.campanha;

public class UsuarioCampanha {
    private Long id;
    /// Associado a Usuario.id
    private Long idUsuario;
    /// Associado a Campanha.id
    private Long idCampanha;

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

}
