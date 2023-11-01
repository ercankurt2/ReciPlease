package de.htwberlin.reciplease.controller;

import de.htwberlin.reciplease.model.Rezept;
import de.htwberlin.reciplease.service.RezeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rezept")
@RequiredArgsConstructor // Konstruktor der Werte entgegennimmt
public class RezeptController {

    private final RezeptService rezeptService;

    @PostMapping
    public Rezept createRezept(@RequestBody Rezept rezept) {
        return rezeptService.createRezept(rezept);
    }
}
