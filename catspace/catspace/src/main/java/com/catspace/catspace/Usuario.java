package com.catspace.catspace;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Usuario {
        private Integer id;
        private String nome;
        private String email;
        private LocalDate dataNascimento;
        private Integer qtdGatosTem;
        private Integer fkRacaFavorita;
        private List<Integer> coresFavoritas;

        public Usuario() {
        }

    public Usuario(Integer id, String nome, String email, LocalDate dataNascimento, Integer qtdGatosTem, Integer fkRacaFavorita, List<Integer> coresFavoritas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.qtdGatosTem = qtdGatosTem;
        this.fkRacaFavorita = fkRacaFavorita;
        this.coresFavoritas = coresFavoritas;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }


        public Usuario(List<Integer> coresFavoritas, Integer fkRacaFavorita) {
            this.coresFavoritas = coresFavoritas;
            this.fkRacaFavorita = fkRacaFavorita;
        }

        public Integer getQtdGatosTem() {
            return qtdGatosTem;
        }

        public void setQtdGatosTem(Integer qtdGatosTem) {
            this.qtdGatosTem = qtdGatosTem;
        }

    public Integer getFkRacaFavorita() {
        return fkRacaFavorita;
    }

    public void setFkRacaFavorita(Integer fkRacaFavorita) {
        this.fkRacaFavorita = fkRacaFavorita;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Integer> getCoresFavoritas() {
        return coresFavoritas;
    }

    public void setCoresFavoritas(List<Integer> coresFavoritas) {
        this.coresFavoritas = coresFavoritas;
    }
}


