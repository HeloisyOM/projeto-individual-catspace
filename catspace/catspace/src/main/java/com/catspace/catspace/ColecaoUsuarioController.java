package com.catspace.catspace;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/colecoes")
public class ColecaoUsuarioController {
    private JdbcTemplate jdbcTemplate;

    public ColecaoUsuarioController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ColecaoUsuario>> listarColecao(@PathVariable Integer id){
        String sql = "SELECT *FROM colecaoUsuario WHERE fkUsuario = ?";

        List<ColecaoUsuario> colecoes = jdbcTemplate.query(
                sql, new BeanPropertyRowMapper<>(ColecaoUsuario.class), id);

        if(colecoes.isEmpty()){
            return  ResponseEntity.status(200).build();
        }
        return  ResponseEntity.status(200).body(colecoes);
    }

    @PostMapping
    public ResponseEntity<ColecaoUsuario> cadastrar(@RequestBody ColecaoUsuario colecao){
        String sql = "INSERT INTO colecaoUsuario(fkUsuario,fkGato,dataGanhou) VALUES(?,?,?)";

        colecao.setDataGanhou(LocalDateTime.now());

       List<ColecaoUsuario> colecoesExistentes = listarColecao(colecao.getFkUsuario()).getBody();
        if(colecoesExistentes == null){
            jdbcTemplate.update(sql, colecao.getFkUsuario(), colecao.getFkGato(), colecao.getDataGanhou());
            return ResponseEntity.status(201).body(colecao);
        }else{
            for (ColecaoUsuario colecoesExistente : colecoesExistentes) {
                if(colecoesExistente.getFkGato().equals(colecao.getFkGato())){
                    return ResponseEntity.status(409).build();
                }
            }
            jdbcTemplate.update(sql, colecao.getFkUsuario(), colecao.getFkGato(), colecao.getDataGanhou());
            return ResponseEntity.status(201).body(colecao);
        }


    }


}
