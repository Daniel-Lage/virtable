package edu.ifal.virtable.domain;

public class Dado {
    private int id;
    /// Associado a SistemaRPG.id
    private int idSistemaRPG;

    private String nome;
    /// Valor máximo do dado; ex.: 6 para um D6
    private int limit;

    public Dado(int id, int idSistemaRPG, String nome, int limit) {
        this.id = id;
        this.idSistemaRPG = idSistemaRPG;
        this.nome = nome;
        this.limit = limit;
    }

    public int getId() {
        return this.id;
    }

    public int getIdSistemaRPG() {
        return this.idSistemaRPG;
    }

    public String getName() {
        return this.nome;
    }

    public int limit() {
        return this.limit;
    }

}
