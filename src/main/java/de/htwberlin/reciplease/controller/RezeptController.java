package de.htwberlin.reciplease.controller;

import de.htwberlin.reciplease.model.Rezept;
import de.htwberlin.reciplease.service.RezeptService;
import lombok.RequiredArgsConstructor;
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

    // HTTP-GET, um eine Liste von Rezepten abzurufen
    @GetMapping
    public List<Rezept> findAll(@RequestParam(required = false) String name) {
        // Wenn der Parameter 'name' in der Anfrage vorhanden ist, wird
        // die Methode searchByName aufgerufen, um Rezepte nach Namen zu durchsuchen
        if (name != null) {
            return rezeptService.searchByName(name);
        }
        // Andernfalls wird die Methode getAllRezepte aufgerufen, um alle Rezepte abzurufen
        return rezeptService.getAllRezepte();
    }
}
