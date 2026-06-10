package edu.ifal.virtable.domain.personagem;

public class Personagem {
    private Long id;
    /// Associado a SistemaRPG.id
    private Long idSistema;
    /// Associado a Usuario.id
    private Long idUsuario;

    private String nome;

    public Personagem(Long id, Long idSistema, Long idUsuario, String nome) {
        this.id = id;
        this.idSistema = idSistema;
        this.idUsuario = idUsuario;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public Long getIdSistema() {
        return idSistema;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getName() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
