package edu.ifal.virtable.domain;

public class SistemaRPG {
    private int id;

    private String nome;

    public SistemaRPG(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return nome;
    }

}
