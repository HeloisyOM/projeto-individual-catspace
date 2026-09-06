package com.catspace.catspace;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/gatos")
public class GatoController {
    private JdbcTemplate jdbcTemplate;

    public GatoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public Integer aleatorizarGatos(){
        String sql = "SELECT * from gato";

        List<Gato> gatos = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Gato.class));

        List<Integer> idsGatos = new ArrayList<>();
        for (Gato gato : gatos) {
            idsGatos.add(gato.getId());
        }

        Random random = new Random();

        return random.nextInt(idsGatos.size());
    }




}
