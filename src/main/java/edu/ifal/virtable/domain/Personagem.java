package edu.ifal.virtable.domain;

public class Personagem {
    private int id;
    /// Associado a SistemaRPG.id
    private int idSistema;
    /// Associado a Usuario.id
    private int idUsuario;

    private String nome;

    public Personagem(int id, int idSistema, int idUsuario, String nome) {
        this.id = id;
        this.idSistema = idSistema;
        this.idUsuario = idUsuario;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public int getIdSistema() {
        return idSistema;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getName() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
