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

    @GetMapping // HTTP-GET, um eine Liste von Rezepten abzurufen
    public ResponseEntity<List<Benutzer>> fetchBenutzer() {
        return ResponseEntity.ok(benutzerService.getAllBenutzer());
    }
}
