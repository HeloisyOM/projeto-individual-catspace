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
@RequestMapping("/personalidades")
public class PersonalidadeController {
    private JdbcTemplate jdbcTemplate;

    public PersonalidadeController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @GetMapping
    public ResponseEntity<List<Personalidade>> listarPerso(){
        String sql = "SELECT * FROM personalidade";

        List<Personalidade> personas = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Personalidade.class));

        if(personas.isEmpty()){
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(200).body(personas);

    }
}
