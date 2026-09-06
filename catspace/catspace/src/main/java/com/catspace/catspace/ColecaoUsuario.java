package com.catspace.catspace;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;

public class ColecaoUsuario {
    private Integer fkUsuario;
    private Integer fkGato;
    private LocalDateTime dataGanhou = LocalDateTime.now();

    public ColecaoUsuario() {
    }

    public ColecaoUsuario(Integer fkUsuario, Integer fkGato, LocalDateTime dataGanhou) {
        this.fkUsuario = fkUsuario;
        this.fkGato = fkGato;
        this.dataGanhou = dataGanhou;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Integer getFkGato() {
        return fkGato;
    }

    public void setFkGato(Integer fkGato) {
        this.fkGato = fkGato;
    }

    public LocalDateTime getDataGanhou() {
        return dataGanhou;
    }

    public void setDataGanhou(LocalDateTime dataGanhou) {
        this.dataGanhou = dataGanhou;
    }
}
