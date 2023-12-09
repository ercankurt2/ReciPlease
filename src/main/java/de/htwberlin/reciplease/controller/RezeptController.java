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

    @GetMapping // HTTP-GET, um eine Liste von Rezepten abzurufen
    public ResponseEntity<List<Rezept>> fetchRezepte() {
        return ResponseEntity.ok(rezeptService.getAllRezepte());
    }

    // HTTP-GET, um ein Rezept anhand seiner ID zu erhalten
    @GetMapping("/{id}") // Der Pfad enthält eine Variable für die ID
    public ResponseEntity<Rezept> getRezeptById(@PathVariable Integer id) {
        Rezept rezept = rezeptService.getRezeptById(id);
        if (rezept != null) {
            return ResponseEntity.ok(rezept);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping // HTTP-POST, um ein neues Rezept zu erstellen
    public Rezept createRezept(@RequestBody Rezept rezept) {
        var valid = validate(rezept);
        if (valid) {
            // Ruft die Methode createRezept des RezeptService auf und gibt das erstellte Rezept zurück
            return rezeptService.createRezept(rezept);
        } else {
            throw new RuntimeException("Validierung fehlgeschlagen. Bitte überprüfe deine Eingaben des Rezepts auf Vollständigkeit.");
        }
    }

    @PutMapping("/{id}") // HTTP-PUT, um ein Rezept anhand seiner ID zu aktualisieren
    public ResponseEntity<Rezept> updateRezept(@PathVariable Integer id, @RequestBody Rezept rezept) {
        Rezept updatedRezept = rezeptService.updateRezept(id, rezept);
        if (updatedRezept != null) {
            return ResponseEntity.ok(updatedRezept);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") // HTTP-DELETE, um ein Rezept anhand seiner ID zu löschen
    public ResponseEntity<Rezept> deleteRezept(@PathVariable Integer id) {
        Rezept rezept = rezeptService.deleteRezept(id);
        if (rezept != null) {
            return ResponseEntity.ok(rezept);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Methode zur Validierung der Eingaben
    private boolean validate(Rezept rezept) {
        return rezept.getTitel() != null && !rezept.getTitel().isBlank() &&
                rezept.getBeschreibung() != null && !rezept.getBeschreibung().isBlank() &&
                rezept.getSchwierigkeitsgrad() != null && !rezept.getSchwierigkeitsgrad().isBlank() &&
                rezept.getZubereitungszeit() != null && !rezept.getZubereitungszeit().isBlank();
    }
}
