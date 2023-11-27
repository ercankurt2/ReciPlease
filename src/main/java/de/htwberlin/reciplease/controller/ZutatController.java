package de.htwberlin.reciplease.controller;

import de.htwberlin.reciplease.model.Zutat;
import de.htwberlin.reciplease.service.ZutatService;
import lombok.RequiredArgsConstructor;
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
    public List<Zutat> findAll(@RequestParam(required = false) String name) {
        // Wenn der Parameter 'name' in der Anfrage vorhanden ist, wird
        // die Methode searchByName aufgerufen, um Zutaten nach Namen zu durchsuchen
        if (name != null) {
            return zutatService.searchByName(name);
        }
        // Andernfalls wird die Methode getAllZutat aufgerufen, um alle Zutaten abzurufen
        return zutatService.getAllZutat();
    }
}
