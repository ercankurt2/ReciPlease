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

    @GetMapping // HTTP-GET, um eine Liste von Rezepten abzurufen
    public ResponseEntity<List<Favoriten>> fetchFavoriten() {
        return ResponseEntity.ok(favoritenService.getAllFavoriten());
    }
}
