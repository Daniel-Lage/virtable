package edu.ifal.virtable.domain;

public class ValorCampo {
    private int id;
    /// Associado a Personagem.id
    private int idPersonagem;
    /// Associado a CampoFicha.id
    private int idCampoFicha;

    /// Valor pode ser do tipo ValorInt ou ValorString
    private Valor valor;

    public ValorCampo(int id, int idPersonagem, int idCampoFicha, Valor valor) {
        this.id = id;
        this.idPersonagem = idPersonagem;
        this.idCampoFicha = idCampoFicha;
        this.valor = valor;
    }

    public int getId() {
        return this.id;
    }

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public int getIdCampoFicha() {
        return idCampoFicha;
    }

    public Valor getValor() {
        return valor;
    }
    
    public void setValor(Valor valor) {
        this.valor = valor;
    }

    public void setIdPersonagem(int idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    public void setIdCampoFicha(int idCampoFicha) {
        this.idCampoFicha = idCampoFicha;
    }

    public TipoValor getTipoValor() {
        if (valor == null) {
            return null;
        }
        return valor.getTipoValor();
    }

}
