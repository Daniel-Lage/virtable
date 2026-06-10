package edu.ifal.virtable.domain.campanha;

public class PersonagemCampanha {
    private Long id;
    /// Associado a Personagem.id
    private Long idPersonagem;
    /// Associado a Campanha.id
    private Long idCampanha;

    public PersonagemCampanha(Long id, Long idPersonagem, Long idCampanha) {
        this.id = id;
        this.idPersonagem = idPersonagem;
        this.idCampanha = idCampanha;
    }

    public Long getId() {
        return id;
    }

    public Long getIdPersonagem() {
        return idPersonagem;
    }

    public Long getIdCampanha() {
        return idCampanha;
    }

}
