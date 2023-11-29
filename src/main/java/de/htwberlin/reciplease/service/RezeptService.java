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

    // Methode zur Generierung einer eindeutigen RezeptID
    public Integer generateRezeptID() {
        // Ruft die Methode count() des RezeptRepository auf, um die Anzahl der Rezepte in der Datenbank zu erhalten
        Integer count = Math.toIntExact(rezeptRepository.count());
        // Erhöht die Anzahl der Rezepte um 1, um eine eindeutige RezeptID zu generieren
        return count + 1;
    }

    // Methode zur Erstellung eines neuen Rezepts in der Datenbank
    public Rezept createRezept(Rezept rezept) {
        // Keine Notwendigkeit, die ID manuell zu generieren, da sie automatisch generiert wird
        // Setzen der eindeutigen RezeptID
        // rezept.setRezeptID(generateRezeptID());

        // Speichert das übergebene Rezept
        rezeptRepository.save(rezept);

        // Gibt das erstellte Rezept zurück
        return rezept;
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

    // Methode, um ein Rezept anhand seiner ID zu erhalten
    public Rezept getRezeptById(Integer id) {
        // Verwendung von findById, um ein Rezept zu finden oder null zurückzugeben
        return rezeptRepository.findById(id).orElse(null);
    }
}
