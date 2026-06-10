package edu.ifal.virtable.domain;

public class Dado {
    private Long id;
    /// Associado a SistemaRPG.id
    private Long idSistemaRPG;

    private String nome;
    /// Valor máximo do dado; ex.: 6 para um D6
    private int limit;

    public Dado(Long id, Long idSistemaRPG, String nome, int limit) {
        this.id = id;
        this.idSistemaRPG = idSistemaRPG;
        this.nome = nome;
        this.limit = limit;
    }

    public Long getId() {
        return this.id;
    }

    public Long getIdSistemaRPG() {
        return this.idSistemaRPG;
    }

    public String getName() {
        return this.nome;
    }

    public int limit() {
        return this.limit;
    }

}
