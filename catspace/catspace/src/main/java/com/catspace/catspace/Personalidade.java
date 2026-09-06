package com.catspace.catspace;

public class Personalidade {
    private Integer id;
    private String nome;

    public Personalidade(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Personalidade() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
