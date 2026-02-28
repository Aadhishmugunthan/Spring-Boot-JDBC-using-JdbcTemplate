package com.example.TddDemo.service;

import com.example.TddDemo.model.Alien;
import com.example.TddDemo.repo.AlienRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlienService {

    private final AlienRepo repo;

    public AlienService(AlienRepo repo) {
        this.repo = repo;
    }

    public void addAlien(Alien alien) {
        repo.save(alien);
    }

    public List<Alien> getAliens() {
        return repo.findAll();
    }

    public Alien getAlienById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Alien not found with id: " + id));
    }
}