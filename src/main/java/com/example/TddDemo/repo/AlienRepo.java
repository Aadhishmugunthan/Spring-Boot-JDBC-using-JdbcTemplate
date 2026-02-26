package com.example.TddDemo.repo;

import com.example.TddDemo.model.Alien;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlienRepo {

    private final JdbcTemplate jdbcTemplate;

    public AlienRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Alien alien) {
        String sql = "INSERT INTO alien (name, tech) VALUES (?, ?)";
        return jdbcTemplate.update(sql, alien.getName(), alien.getTech());
    }

    public List<Alien> findAll() {
        String sql = "SELECT * FROM alien";

        RowMapper<Alien> mapper = (rs, rowNum) ->
                new Alien(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("tech")
                );

        return jdbcTemplate.query(sql, mapper);
    }

    public Alien findById(int id) {
        String sql = "SELECT * FROM alien WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new Alien(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("tech")
                ), id);
    }
}