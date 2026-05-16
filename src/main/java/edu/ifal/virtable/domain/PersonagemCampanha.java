package edu.ifal.virtable.domain;

public class PersonagemCampanha {
    private int id;
    /// Associado a Personagem.id
    private int idPersonagem;
    /// Associado a Campanha.id
    private int idCampanha;

    public PersonagemCampanha(int id, int idPersonagem, int idCampanha) {
        this.id = id;
        this.idPersonagem = idPersonagem;
        this.idCampanha = idCampanha;
    }

    public int getId() {
        return id;
    }

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public int getIdCampanha() {
        return idCampanha;
    }

}
