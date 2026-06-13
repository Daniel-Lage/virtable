package edu.ifal.virtable.domain.ficha;

import edu.ifal.virtable.domain.personagem.Personagem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "valores_campos")
public class ValorCampo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Associado a CampoFicha.id
    @ManyToOne
    @JoinColumn(name = "id_campo_ficha", nullable = false)
    private CampoFicha campoFicha;

    // Associado a Personagem.id
    @ManyToOne
    @JoinColumn(name = "id_personagem", nullable = false)
    private Personagem personagem;

    @NotBlank(message = "O valor é obrigatório")
    @Size(max = 255, message = "O valor deve ter no máximo 255 caracteres")
    private String valor;

    public ValorCampo() {
    }

    public ValorCampo(Long id, CampoFicha campoFicha, Personagem personagem, String valor) {
        this.id = id;
        this.campoFicha = campoFicha;
        this.personagem = personagem;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public CampoFicha getCampoFicha() {
        return campoFicha;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public String getValor() {
        return valor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCampoFicha(CampoFicha campoFicha) {
        this.campoFicha = campoFicha;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
