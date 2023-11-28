package de.htwberlin.reciplease.controller;

import de.htwberlin.reciplease.model.Kategorie;
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
}
