package com.catspace.catspace;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private JdbcTemplate jdbcTemplate;

    public UsuarioController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario user ){
        String sqlUsuario = "INSERT INTO usuario(nome,email,dataNascimento,qtdGatosTem) VALUES(?,?,?,?)";

        LocalDate dataAtual = LocalDate.now();

        if(user.getNome() == null || user.getEmail().isEmpty() || !user.getEmail().contains("@") || user.getDataNascimento().equals(dataAtual) || user.getDataNascimento().isAfter(dataAtual) || user.getQtdGatosTem()<0){
            return ResponseEntity.status(400).build();
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sqlUsuario,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,user.getNome());
            ps.setString(2,user.getEmail());
            ps.setObject(3,user.getDataNascimento());
            ps.setInt(4,user.getQtdGatosTem());

            return ps;
        },keyHolder);

        int idUserGerado = keyHolder.getKeyAs(Integer.class);
        user.setId(idUserGerado);

        String sqlPreferencia = "INSERT INTO preferencias (fkUsuario, fkRacaFavorita) VALUES (?, ?)";
        jdbcTemplate.update(sqlPreferencia, idUserGerado, user.getFkRacaFavorita());

        String sqlCores = "INSERT INTO corFavUsuario (fkUsuario, fkCor) VALUES (?, ?)";
        for (Integer idCor : user.getCoresFavoritas()) {
            jdbcTemplate.update(sqlCores, idUserGerado, idCor);
        }


        return ResponseEntity.status(201).body(user);
    }

}
