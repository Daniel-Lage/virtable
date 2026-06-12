package edu.ifal.virtable.domain.ficha;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "valores_campos")
public class ValorCampo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Associado a CampoFicha.id
    @NotNull(message = "O campo da ficha é obrigatório")
    private Long idCampoFicha;

    // Associado a Personagem.id
    @NotNull(message = "O personagem é obrigatório")
    private Long idPersonagem;

    @NotBlank(message = "O valor é obrigatório")
    @Size(max = 255, message = "O valor deve ter no máximo 255 caracteres")
    private String valor;

    public ValorCampo() {
    }

    public ValorCampo(Long id, Long idCampoFicha, Long idPersonagem, String valor) {
        this.id = id;
        this.idCampoFicha = idCampoFicha;
        this.idPersonagem = idPersonagem;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public Long getIdCampoFicha() {
        return idCampoFicha;
    }

    public Long getIdPersonagem() {
        return idPersonagem;
    }

    public String getValor() {
        return valor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdCampoFicha(Long idCampoFicha) {
        this.idCampoFicha = idCampoFicha;
    }

    public void setIdPersonagem(Long idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}