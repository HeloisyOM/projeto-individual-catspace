package com.catspace.catspace;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private JdbcTemplate jdbcTemplate;

    public UsuarioController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> pegarUsuario(@PathVariable Integer idUsuario){
        String sql = "SELECT * FROM usuario WHERE id=?";

        try{
            Usuario user =jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Usuario.class), idUsuario);
            user.setId(idUsuario);

            return ResponseEntity.status(200).body(user);
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario(){
        String sql = "SELECT * FROM usuario";

        List<Usuario> usuarios = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Usuario.class));

        if(usuarios.isEmpty()){
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(200).body(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario user ){
        String sqlUsuario = "INSERT INTO usuario(nome,email,dataNascimento,qtdGatosTem,senha) VALUES(?,?,?,?,?)";

        LocalDate dataAtual = LocalDate.now();

        if(user.getNome() == null || user.getEmail().isEmpty() || !user.getEmail().contains("@") || user.getDataNascimento().equals(dataAtual) || user.getDataNascimento().isAfter(dataAtual) || user.getQtdGatosTem()<0 || user.getSenha().length()<8){
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
            ps.setString(5,user.getSenha());


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
