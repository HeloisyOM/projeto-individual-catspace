package com.catspace.catspace;

public class Gato {
    private Integer id;
    private String nome;
    private String urlFoto;
    private Integer fkRaca;

    public Gato() {
    }

    public Gato(Integer id, String nome, String urlFoto, Integer fkRaca) {
        this.id = id;
        this.nome = nome;
        this.urlFoto = urlFoto;
        this.fkRaca = fkRaca;
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

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public Integer getFkRaca() {
        return fkRaca;
    }

    public void setFkRaca(Integer fkRaca) {
        this.fkRaca = fkRaca;
    }
}
