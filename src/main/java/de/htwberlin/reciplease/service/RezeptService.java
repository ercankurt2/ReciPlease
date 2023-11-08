package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Rezept;
import de.htwberlin.reciplease.repository.RezeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service-Annotation kennzeichnet diese Klasse als einen Spring-Service,
// der die Geschäftslogik der Anwendung implementiert
@Service
@RequiredArgsConstructor // Lombok-Anmerkung, die einen Konstruktor erstellt
public class RezeptService {

    // Injektion des RezeptRepository, um auf Datenbankoperationen zuzugreifen
    private final RezeptRepository rezeptRepository;

    // Methode zur Erstellung eines neuen Rezepts in der Datenbank
    public Rezept createRezept(Rezept rezept) {
        rezeptRepository.save(rezept); // Speichert das übergebene Rezept
        return rezept; // Gibt das erstellte Rezept zurück
    }

    // Methode zur Abfrage aller Rezepte in der Datenbank
    public List<Rezept> getAllRezepte() {
        return rezeptRepository.findAll(); // Ruft alle Rezepte ab
    }

    // Methode zur Suche von Rezepten anhand des Namens
    public List<Rezept> searchByName(String titel) {
        // Ruft eine benutzerdefinierte Suche nach Rezepten anhand des Titels ab
        return rezeptRepository.findRezepteByTitelContaining(titel);
    }
}
