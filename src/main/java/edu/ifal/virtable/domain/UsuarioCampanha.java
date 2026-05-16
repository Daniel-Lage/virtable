package edu.ifal.virtable.domain;

public class UsuarioCampanha {
    private int id;
    /// Associado a Usuario.id
    private int idUsuario;
    /// Associado a Campanha.id
    private int idCampanha;

    public UsuarioCampanha(int id, int idUsuario, int idCampanha) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idCampanha = idCampanha;
    }

    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdCampanha() {
        return idCampanha;
    }

}
