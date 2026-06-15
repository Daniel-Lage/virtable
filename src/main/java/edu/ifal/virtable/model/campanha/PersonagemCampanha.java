package edu.ifal.virtable.model.campanha;

import edu.ifal.virtable.model.Item;
import edu.ifal.virtable.model.personagem.Personagem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "personagem_campanha")
public class PersonagemCampanha implements Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personagem_campanha")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_personagem", nullable = false)
    private Personagem personagem;

    @ManyToOne
    @JoinColumn(name = "id_campanha", nullable = false)
    private Campanha campanha;

    public PersonagemCampanha() {
    }

    public PersonagemCampanha(Long id, Personagem personagem, Campanha campanha) {
        this.id = id;
        this.personagem = personagem;
        this.campanha = campanha;
    }

    public Long getId() {
        return id;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }
}
