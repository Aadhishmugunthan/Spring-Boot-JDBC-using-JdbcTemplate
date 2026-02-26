package com.example.TddDemo;

import com.example.TddDemo.model.Alien;
import com.example.TddDemo.repo.AlienRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TddDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TddDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(AlienRepo repo) {
        return args -> {
            Alien alien = new Alien();
            alien.setName("Navin");
            alien.setTech("Java");

            repo.save(alien);

            System.out.println(repo.findAll());
        };
    }
}