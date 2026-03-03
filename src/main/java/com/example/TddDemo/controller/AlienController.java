package com.example.TddDemo.controller;

import com.example.TddDemo.model.Alien;
import com.example.TddDemo.service.AlienService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/aliens")
public class AlienController {

    private final AlienService service;

    public AlienController(AlienService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Alien> addAlien(@RequestBody Alien alien) {
        Alien saved = service.addAlien(alien);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Alien>> getAll() {
        return ResponseEntity.ok(service.getAliens());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Alien> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getAlienById(id));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.deleteAlien(id);
        return ResponseEntity.noContent().build();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Alien> update(@PathVariable int id,
                                        @RequestBody Alien alien) {
        alien.setId(id);
        return ResponseEntity.ok(service.updateAlien(alien));
    }
}