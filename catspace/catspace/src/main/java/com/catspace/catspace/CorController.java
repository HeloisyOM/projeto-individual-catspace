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
@RequestMapping("/cores")
public class CorController {
    private JdbcTemplate jdbcTemplate;

    public CorController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @GetMapping
    public ResponseEntity<List<Cor>> listarCores(){
        String sql = "SELECT * FROM corGato";

        List<Cor> cores = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Cor.class));

        if(cores.isEmpty()){
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(200).body(cores);

    }
}
