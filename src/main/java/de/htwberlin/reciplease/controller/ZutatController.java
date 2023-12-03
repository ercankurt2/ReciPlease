package de.htwberlin.reciplease.controller;

import de.htwberlin.reciplease.model.Rezept;
import de.htwberlin.reciplease.model.Zutat;
import de.htwberlin.reciplease.service.ZutatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zutat")
@RequiredArgsConstructor // Konstruktor, der Werte entgegennimmt
public class ZutatController {

    // Injektion des ZutatService
    private final ZutatService zutatService;

    @PostMapping // HTTP-POST, um eine neue Zutat zu erstellen
    public Zutat createZutat(@RequestBody Zutat zutat) {
        // Ruft die Methode createZutat des ZutatService auf und gibt die erstellte Zutat zurück
        return zutatService.createZutat(zutat);
    }

    @GetMapping // HTTP-GET, um eine Liste von Zutaten abzurufen
    public ResponseEntity<List<Zutat>> fetchZutaten() {
        return ResponseEntity.ok(zutatService.getAllZutat());
    }
    // HTTP-GET, um eine Zutat anhand seiner ID zu erhalten

    @GetMapping("/{id}") // Der Pfad enthält eine Variable für die ID
    public ResponseEntity<Zutat> getZutattById(@PathVariable Integer id) {
        Zutat zutat = zutatService.getZutatById(id);
        if (zutat != null) {
            return ResponseEntity.ok(zutat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}") // HTTP-PUT, um eine Zutat anhand ihrer ID zu aktualisieren
    public ResponseEntity<Zutat> updateZutat(@PathVariable Integer id, @RequestBody Zutat zutat) {
        Zutat updatedZutat = zutatService.updateZutat(id, zutat);
        if (updatedZutat != null) {
            return ResponseEntity.ok(updatedZutat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") // HTTP-DELETE, um eine Zutat anhand ihrer ID zu löschen
    public ResponseEntity<Zutat> deleteZutat(@PathVariable Integer id) {
        Zutat zutat = zutatService.deleteZutat(id);
        if (zutat != null) {
            return ResponseEntity.ok(zutat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

