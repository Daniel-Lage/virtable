package edu.ifal.virtable.domain.campanha;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "personagens_campanhas")
public class PersonagemCampanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Associado a Personagem.id
    @NotNull(message = "O personagem é obrigatório")
    private Long idPersonagem;

    // Associado a Campanha.id
    @NotNull(message = "A campanha é obrigatória")
    private Long idCampanha;

    public PersonagemCampanha() {
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdPersonagem(Long idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    public void setIdCampanha(Long idCampanha) {
        this.idCampanha = idCampanha;
    }
}