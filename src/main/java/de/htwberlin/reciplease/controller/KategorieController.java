package de.htwberlin.reciplease.controller;

import de.htwberlin.reciplease.model.Kategorie;
import de.htwberlin.reciplease.model.Rezept;
import de.htwberlin.reciplease.service.KategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kategorie")
@RequiredArgsConstructor // Konstruktor, der Werte entgegennimmt
public class KategorieController {

    // Injektion des KategorieService
    private final KategorieService kategorieService;

    @PostMapping // HTTP-POST, um eine neue Kategorie zu erstellen
    public Kategorie createKategorie(@RequestBody Kategorie kategorie) {
        // Ruft die Methode createKategorie des KategorieService auf und gibt die erstellte Kategorie zurück
        return kategorieService.createKategorie(kategorie);
    }

    @GetMapping // HTTP-GET, um eine Liste von Kategorien abzurufen
    public ResponseEntity<List<Kategorie>> fetchKategorien() {
        return ResponseEntity.ok(kategorieService.getAllKategorie());
    }

    // HTTP-GET, um eine Kategorie anhand ihrer ID zu erhalten
    @GetMapping("/{id}") // Der Pfad enthält eine Variable für die ID
    public ResponseEntity<Kategorie> getKategorieById(@PathVariable Integer id) {
        Kategorie kategorie = kategorieService.getKategorieById(id);
        if (kategorie != null) {
            return ResponseEntity.ok(kategorie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}") // HTTP-PUT, um eine Kategorie anhand ihrer ID zu aktualisieren
    public ResponseEntity<Kategorie> updateKategorie(@PathVariable Integer id, @RequestBody Kategorie kategorie) {
        Kategorie updatedKategorie = kategorieService.updateKategorie(id, kategorie);
        if (updatedKategorie != null) {
            return ResponseEntity.ok(updatedKategorie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") // HTTP-DELETE, um eine Kategorie anhand seiner ID zu löschen
    public ResponseEntity<Kategorie> deleteKategorie(@PathVariable Integer id) {
        Kategorie kategorie = kategorieService.deleteKategorie(id);
        if (kategorie != null) {
            return ResponseEntity.ok(kategorie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

