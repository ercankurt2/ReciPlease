package de.htwberlin.reciplease.controller;

import de.htwberlin.reciplease.model.Rezept;
import de.htwberlin.reciplease.service.RezeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rezept")
@RequiredArgsConstructor // Konstruktor, der Werte entgegennimmt
public class RezeptController {

    // Injektion des RezeptService
    private final RezeptService rezeptService;

    @PostMapping // HTTP-POST, um ein neues Rezept zu erstellen
    public Rezept createRezept(@RequestBody Rezept rezept) {
        // Ruft die Methode createRezept des RezeptService auf und gibt das erstellte Rezept zurück
        return rezeptService.createRezept(rezept);
    }

    @GetMapping // HTTP-GET, um eine Liste von Rezepten abzurufen
    public ResponseEntity<List<Rezept>> fetchRezepte() {
        return ResponseEntity.ok(rezeptService.getAllRezepte());
    }
}
