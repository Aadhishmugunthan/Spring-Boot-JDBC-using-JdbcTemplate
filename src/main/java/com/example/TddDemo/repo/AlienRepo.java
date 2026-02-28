package com.example.TddDemo.repo;

import com.example.TddDemo.model.Alien;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlienRepo {

    private final JdbcTemplate jdbcTemplate;

    public AlienRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Common RowMapper
    private final RowMapper<Alien> alienRowMapper = (rs, rowNum) ->
            new Alien(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("tech")
            );

    // INSERT (Oracle auto-generates ID)
    public int save(Alien alien) {
        String sql = "INSERT INTO alien (name, tech) VALUES (?, ?)";
        return jdbcTemplate.update(sql,
                alien.getName(),
                alien.getTech()
        );
    }

    // SELECT ALL
    public List<Alien> findAll() {
        String sql = "SELECT id, name, tech FROM alien";
        return jdbcTemplate.query(sql, alienRowMapper);
    }

    // SELECT BY ID
    public Optional<Alien> findById(int id) {
        String sql = "SELECT id, name, tech FROM alien WHERE id = ?";

        try {
            Alien alien = jdbcTemplate.queryForObject(sql, alienRowMapper, id);
            return Optional.ofNullable(alien);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    // DELETE
    public int deleteById(int id) {
        String sql = "DELETE FROM alien WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // UPDATE
    public int update(Alien alien) {
        String sql = "UPDATE alien SET name = ?, tech = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                alien.getName(),
                alien.getTech(),
                alien.getId()
        );
    }
}