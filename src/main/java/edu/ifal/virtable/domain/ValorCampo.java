package edu.ifal.virtable.domain;

public class ValorCampo {
    private int id;
    /// Associado a Personagem.id
    private int idPersonagem;
    /// Associado a CampoFicha.id
    private int idCampoFicha;

    /// Valor pode ser do tipo ValorInt ou ValorString
    private Valor valor;

    public ValorCampo(int id, int idPersonagem, int idCampoFicha) {
        this.id = id;
        this.idPersonagem = idPersonagem;
        this.idCampoFicha = idCampoFicha;
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

    public TipoValor getTipoValor() {
        return null;
    }

}
