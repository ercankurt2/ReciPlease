package de.htwberlin.reciplease.controller;

import de.htwberlin.reciplease.model.Ernaehrungsinformationen;
import de.htwberlin.reciplease.service.ErnaehrungsinformationenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ernaehrungsinformationen")
@RequiredArgsConstructor // Konstruktor, der Werte entgegennimmt
public class ErnaehrungsinformationenController {

    // Injektion des ErnaehrungsinformationenService
    private final ErnaehrungsinformationenService ernaehrungsinformationenService;

    @PostMapping // HTTP-POST, um eine neue Ernährungsinformation zu erstellen
    public Ernaehrungsinformationen createErnaehrungsinformationen(@RequestBody Ernaehrungsinformationen ernaehrungsinformationen) {
        // Ruft die Methode createErnaehrungsinformationen des ErnaehrungsinformationenService auf und gibt die erstellte Ernährungsinformation zurück
        return ernaehrungsinformationenService.createErnaehrungsinformationen(ernaehrungsinformationen);
    }

    @GetMapping // HTTP-GET, um eine Liste von Ernährungsinformationen abzurufen
    public ResponseEntity<List<Ernaehrungsinformationen>> fetchErnaehrungsinformationen() {
        // Es wird die Methode getAllErnaehrungsinformationen aufgerufen, um alle Ernährungsinformationen abzurufen
        return ResponseEntity.ok(ernaehrungsinformationenService.getAllErnaehrungsinformationen());
    }

}
