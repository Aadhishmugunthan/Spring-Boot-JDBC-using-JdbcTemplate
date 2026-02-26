package com.example.TddDemo.controller;

import com.example.TddDemo.model.Alien;
import com.example.TddDemo.service.AlienService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aliens")
public class AlienController {

    private final AlienService service;

    public AlienController(AlienService service) {
        this.service = service;
    }

    // POST
    @PostMapping
    public String addAlien(@RequestBody Alien alien) {
        service.addAlien(alien);
        return "Alien Added Successfully";
    }

    // GET ALL
    @GetMapping
    public List<Alien> getAll() {
        return service.getAliens();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Alien getById(@PathVariable int id) {
        return service.getAlienById(id);
    }
}