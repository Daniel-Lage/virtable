package edu.ifal.virtable.domain;

public class Campanha {
    private int id;
    /// Associado a Usuario.id
    private int idMestre;
    /// Associado a SistemaRPG.id
    private int idSistemaRPG;

    private String nome;

    public Campanha(int id, int idMestre, int idSistemaRPG, String nome, Usuario mestre, SistemaRPG sistemaRPG,
            Usuario[] jogadores) {
        this.id = id;
        this.idMestre = idMestre;
        this.idSistemaRPG = idSistemaRPG;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public int getIdMestre() {
        return idMestre;
    }

    public int getIdSistemaRPG() {
        return idSistemaRPG;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
