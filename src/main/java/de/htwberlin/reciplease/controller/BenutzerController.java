package de.htwberlin.reciplease.controller;

import de.htwberlin.reciplease.model.Benutzer;
import de.htwberlin.reciplease.service.BenutzerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/benutzer")
@RequiredArgsConstructor // Konstruktor, der Werte entgegennimmt
public class BenutzerController {

    // Injektion des BenutzerService
    private final BenutzerService benutzerService;

    @PostMapping // HTTP-POST, um einen neuen Benutzer zu erstellen
    public Benutzer createBenutzer(@RequestBody Benutzer benutzer) {
        // Ruft die Methode createBenutzer des BenutzerService auf und gibt den erstellten Benutzer zurück
        return benutzerService.createBenutzer(benutzer);
    }

    @GetMapping // HTTP-GET, um eine Liste von Benutzern abzurufen
    public ResponseEntity<List<Benutzer>> fetchBenutzer() {
        return ResponseEntity.ok(benutzerService.getAllBenutzer());
    }

    // HTTP-GET, um einen Benutzer anhand seiner ID zu erhalten
    @GetMapping("/{id}") // Der Pfad enthält eine Variable für die ID
    public ResponseEntity<Benutzer> getBenutzerById(@PathVariable Integer id) {
        Benutzer benutzer = benutzerService.getBenutzerById(id);
        if (benutzer != null) {
            return ResponseEntity.ok(benutzer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}") // HTTP-PUT, um einen Benutzer anhand seiner ID zu aktualisieren
    public ResponseEntity<Benutzer> updateBenutzer(@PathVariable Integer id, @RequestBody Benutzer benutzer) {
        Benutzer updatedBenutzer = benutzerService.updateBenutzer(id, benutzer);
        if (updatedBenutzer != null) {
            return ResponseEntity.ok(updatedBenutzer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") // HTTP-DELETE, um einen Benutzer anhand seiner ID zu löschen
    public ResponseEntity<Benutzer> deleteBenutzer(@PathVariable Integer id) {
        Benutzer benutzer = benutzerService.deleteBenutzer(id);
        if (benutzer != null) {
            return ResponseEntity.ok(benutzer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
