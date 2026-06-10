package edu.ifal.virtable.domain;

public class Campanha {
    private Long id;
    /// Associado a Usuario.id
    private Long idMestre;
    /// Associado a SistemaRPG.id
    private Long idSistemaRPG;

    private String nome;

    public Campanha(Long id, Long idMestre, Long idSistemaRPG, String nome, Usuario mestre, SistemaRPG sistemaRPG,
            Usuario[] jogadores) {
        this.id = id;
        this.idMestre = idMestre;
        this.idSistemaRPG = idSistemaRPG;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public Long getIdMestre() {
        return idMestre;
    }

    public Long getIdSistemaRPG() {
        return idSistemaRPG;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
