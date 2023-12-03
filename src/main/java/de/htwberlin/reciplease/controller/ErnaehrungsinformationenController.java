package de.htwberlin.reciplease.controller;

import de.htwberlin.reciplease.model.Ernaehrungsinformationen;
import de.htwberlin.reciplease.model.Rezept;
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

    // HTTP-GET, um Ernährungsinformationen anhand seiner ID zu erhalten
    @GetMapping("/{id}") // Der Pfad enthält eine Variable für die ID
    public ResponseEntity<Ernaehrungsinformationen> getErnaehrungsinformationenById(@PathVariable Integer id) {
        Ernaehrungsinformationen ernaehrungsinformationen = ernaehrungsinformationenService.getErnaehrungsinformationenById(id);
        if (ernaehrungsinformationen != null) {
            return ResponseEntity.ok(ernaehrungsinformationen);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}") // HTTP-PUT, um Ernährungsinformationen anhand seiner ID zu aktualisieren
    public ResponseEntity<Ernaehrungsinformationen> updateErnaehrungsinformationen(@PathVariable Integer id, @RequestBody Ernaehrungsinformationen ernaehrungsinformationen) {
        Ernaehrungsinformationen updatedErnaehrungsinformationen = ernaehrungsinformationenService.updateErnaehrungsinformationen(id, ernaehrungsinformationen);
        if (updatedErnaehrungsinformationen != null) {
            return ResponseEntity.ok(updatedErnaehrungsinformationen);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") // HTTP-DELETE, um Ernährungsinformationen anhand seiner ID zu löschen
    public ResponseEntity<Ernaehrungsinformationen> deleteErnaehrungsinformationen(@PathVariable Integer id) {
        Ernaehrungsinformationen ernaehrungsinformationen = ernaehrungsinformationenService.deleteErnaehrungsinformationen(id);
        if (ernaehrungsinformationen != null) {
            return ResponseEntity.ok(ernaehrungsinformationen);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
