package edu.ifal.virtable.model.ficha;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.ifal.virtable.helper.TipoValor;
import edu.ifal.virtable.helper.Valor;
import edu.ifal.virtable.helper.ValorIntFactory;
import edu.ifal.virtable.helper.ValorStringFactory;
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
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "opcao_valor")
public class OpcaoValor implements Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_opcao_valor", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_campo_ficha", nullable = false)
    private CampoFicha campoFicha;

    @NotBlank(message = "O valor é obrigatório")
    @Size(max = 255, message = "O valor deve ter no máximo 255 caracteres")
    @Column(name = "vlr_campo", nullable = false)
    private String valor;

    @Transient
    private Valor valorInterno;

    public OpcaoValor() {
    }

    public OpcaoValor(Long id, CampoFicha campoFicha, Personagem personagem, String valor) {
        this.id = id;
        this.campoFicha = campoFicha;
        this.valor = valor;

        TipoValor tipoValor = campoFicha.getTipoValor();

        if (tipoValor != null) {
            switch (tipoValor) {
                case TipoValor.String:
                    valorInterno = new ValorIntFactory().criarValor(valor);
                    break;
                case TipoValor.Int:
                    valorInterno = new ValorStringFactory().criarValor(valor);
                    break;
                default:
                    break;
            }
        }

    }

    public Long getId() {
        return id;
    }

    public CampoFicha getCampoFicha() {
        return campoFicha;
    }

    public String getValor() {
        return valor;
    }

    @JsonIgnore
    public Valor getValorInterno() {
        return valorInterno;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCampoFicha(CampoFicha campoFicha) {
        this.campoFicha = campoFicha;
    }

    public void setValor(String valor) {
        this.valor = valor;

        TipoValor tipoValor = campoFicha.getTipoValor();

        switch (tipoValor) {
            case TipoValor.String:
                valorInterno = new ValorIntFactory().criarValor(valor);
                break;
            case TipoValor.Int:
                valorInterno = new ValorStringFactory().criarValor(valor);
                break;

            default:
                break;
        }
    }
}
