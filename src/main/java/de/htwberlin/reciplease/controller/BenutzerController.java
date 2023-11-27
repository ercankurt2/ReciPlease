package de.htwberlin.reciplease.controller;

import de.htwberlin.reciplease.model.Benutzer;
import de.htwberlin.reciplease.service.BenutzerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/benutzer")
@RequiredArgsConstructor // Konstruktor, der Werte entgegennimmt
public class BenutzerController {

    // Injektion des BenutzerService
    private final BenutzerService benutzerService;

    @PostMapping // HTTP-POST, um ein neues Rezept zu erstellen
    public Benutzer createBenutzer(@RequestBody Benutzer benutzer) {
        // Ruft die Methode createBenutzer des BenutzerService auf und gibt den erstellten Benutzer zurück
        return benutzerService.createBenutzer(benutzer);
    }

    @GetMapping // HTTP-GET, um eine Liste von Benutzern abzurufen
    public List<Benutzer> findAll(@RequestParam(required = false) String name) {
        // Wenn der Parameter 'name' in der Anfrage vorhanden ist, wird
        // die Methode searchByName aufgerufen, um Benutzer nach Namen zu durchsuchen
        if (name != null) {
            return benutzerService.searchByName(name);
        }
        // Andernfalls wird die Methode getAllBenutzer aufgerufen, um alle Benutzer abzurufen
        return benutzerService.getAllBenutzer();
    }
}
