package com.catspace.catspace;

import org.springframework.dao.EmptyResultDataAccessException;
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
    public ResponseEntity<List<Gato>> listarGatos(){
        String sql = "SELECT * FROM gato";

        List<Gato> gatos = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Gato.class));

        if(gatos.isEmpty()){
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(200).body(gatos);
    }
    @GetMapping("/aleatorizar")
    public Integer aleatorizarGatos(){
        String sql = "SELECT * from gato";

        List<Gato> gatos = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Gato.class));

        List<Integer> idsGatos = new ArrayList<>();
        for (Gato gato : gatos) {
            idsGatos.add(gato.getId());
        }

        Random random = new Random();

        int indiceAleatorio = random.nextInt(idsGatos.size());
        return idsGatos.get(indiceAleatorio);
    }

    @GetMapping("/{idGato}")
    public ResponseEntity<Gato> pegarGato(@PathVariable Integer idGato){
        String sql = "SELECT nome,urlFoto FROM gato WHERE id=?";

        try{
            Gato gato =jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Gato.class), idGato);
            gato.setId(idGato);
            return ResponseEntity.status(200).body(gato);
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.status(404).build();
        }
    }




}
