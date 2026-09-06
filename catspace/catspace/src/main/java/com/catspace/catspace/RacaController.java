package com.catspace.catspace;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/racas")
public class RacaController {
    private JdbcTemplate jdbcTemplate;

    public RacaController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @GetMapping
    public ResponseEntity<List<Raca>> listarRacas(){
        String sql = "SELECT * FROM racaGato";

        List<Raca> racas = jdbcTemplate.query(sql,
        new BeanPropertyRowMapper<>(Raca.class));

        if(racas.isEmpty()){
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(200).body(racas);
    }




}
