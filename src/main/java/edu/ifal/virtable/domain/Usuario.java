package edu.ifal.virtable.domain;

public class Usuario {
    private int id;
    private String nome;

    public Usuario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.nome;
    }
}