package edu.ifal.virtable.domain;

public class CampoFicha {
    private int id;
    /// Associado a SistemaRPG.id
    private int idSistemaRPG;

    private String nome;
    private TipoCampo tipoCampo;
    private TipoValor tipoValor;

    public CampoFicha(int id, int idSistemaRPG, String nome, TipoCampo tipoCampo, TipoValor tipoValor) {
        this.id = id;
        this.idSistemaRPG = idSistemaRPG;
        this.nome = nome;
        this.tipoCampo = tipoCampo;
        this.tipoValor = tipoValor;
    }

    public int getId() {
        return id;
    }

    public int getIdSistemaRPG() {
        return idSistemaRPG;
    }

    public String getNome() {
        return nome;
    }

    public TipoCampo getTipoCampo() {
        return tipoCampo;
    }

    public TipoValor getTipoValor() {
        return tipoValor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipoCampo(TipoCampo tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    public void setTipoValor(TipoValor tipoValor) {
        this.tipoValor = tipoValor;
    }

    public void setIdSistemaRPG(int idSistemaRPG) {
        this.idSistemaRPG = idSistemaRPG;
    }
}
