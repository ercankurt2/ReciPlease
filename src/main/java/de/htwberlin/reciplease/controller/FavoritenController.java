package de.htwberlin.reciplease.controller;

import de.htwberlin.reciplease.model.Favoriten;
import de.htwberlin.reciplease.service.FavoritenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoriten")
@RequiredArgsConstructor // Konstruktor, der Werte entgegennimmt
public class FavoritenController {

    // Injektion des FavoritenService
    private final FavoritenService favoritenService;

    @PostMapping // HTTP-POST, um einen neuen Favoriten zu erstellen
    public Favoriten createFavoriten(@RequestBody Favoriten favoriten) {
        // Ruft die Methode createFavoriten des FavoritenService auf und gibt den erstellten Favoriten zurück
        return favoritenService.createFavoriten(favoriten);
    }

    @GetMapping // HTTP-GET, um eine Liste von Favoriten abzurufen
    public ResponseEntity<List<Favoriten>> fetchFavoriten() {
        return ResponseEntity.ok(favoritenService.getAllFavoriten());
    }

    // HTTP-GET, um einen Favoriten anhand seiner ID zu erhalten
    @GetMapping("/{id}") // Der Pfad enthält eine Variable für die ID
    public ResponseEntity<Favoriten> getFavoritenById(@PathVariable Integer id) {
        Favoriten favoriten = favoritenService.getFavoritenById(id);
        if (favoriten != null) {
            return ResponseEntity.ok(favoriten);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}") // HTTP-PUT, um einen Favoriten anhand seiner ID zu aktualisieren
    public ResponseEntity<Favoriten> updateFavoriten(@PathVariable Integer id, @RequestBody Favoriten favoriten) {
        Favoriten updatedFavoriten = favoritenService.updateFavoriten(id, favoriten);
        if (updatedFavoriten != null) {
            return ResponseEntity.ok(updatedFavoriten);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") // HTTP-DELETE, um einen Favoriten anhand seiner ID zu löschen
    public ResponseEntity<Favoriten> deleteFavoriten(@PathVariable Integer id) {
        Favoriten favoriten = favoritenService.deleteFavoriten(id);
        if (favoriten != null) {
            return ResponseEntity.ok(favoriten);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
