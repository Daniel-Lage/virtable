package edu.ifal.virtable.domain;

public class CampoFicha {
    private Long id;
    /// Associado a SistemaRPG.id
    private Long idSistemaRPG;

    private String nome;
    private TipoCampo tipoCampo;
    private TipoValor tipoValor;

    public CampoFicha(Long id, Long idSistemaRPG, String nome, TipoCampo tipoCampo, TipoValor tipoValor) {
        this.id = id;
        this.idSistemaRPG = idSistemaRPG;
        this.nome = nome;
        this.tipoCampo = tipoCampo;
        this.tipoValor = tipoValor;
    }

    public Long getId() {
        return id;
    }

    public Long getIdSistemaRPG() {
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

    public void setIdSistemaRPG(Long idSistemaRPG) {
        this.idSistemaRPG = idSistemaRPG;
    }
}
