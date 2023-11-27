package de.htwberlin.reciplease.controller;

import de.htwberlin.reciplease.model.Kategorie;
import de.htwberlin.reciplease.service.KategorieService;
import lombok.RequiredArgsConstructor;
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
    public List<Kategorie> findAll(@RequestParam(required = false) String name) {
        // Wenn der Parameter 'name' in der Anfrage vorhanden ist, wird
        // die Methode searchByName aufgerufen, um Kategorien nach Namen zu durchsuchen
        if (name != null) {
            return kategorieService.searchByName(name);
        }
        // Andernfalls wird die Methode getAllKategorie aufgerufen, um alle Kategorien abzurufen
        return kategorieService.getAllKategorie();
    }
}
