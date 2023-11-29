package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Zutat;
import de.htwberlin.reciplease.repository.ZutatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service-Annotation kennzeichnet diese Klasse als einen Spring-Service,
// der die Geschäftslogik der Anwendung implementiert
@Service
@RequiredArgsConstructor // Lombok-Anmerkung, die einen Konstruktor erstellt
public class ZutatService {

    // Injektion des ZutatRepository, um auf Datenbankoperationen zuzugreifen
    private final ZutatRepository zutatRepository;

    // Methode zur Generierung einer eindeutigen ZutatID
    public Integer generateZutatID() {
        // Ruft die Methode count() des ZutatRepository auf, um die Anzahl der Zutaten in der Datenbank zu erhalten
        Integer count = Math.toIntExact(zutatRepository.count());
        // Erhöht die Anzahl der Zutaten um 1, um eine eindeutige ZutatID zu generieren
        return count + 1;
    }

    // Methode zur Erstellung einer neuen Zutat in der Datenbank
    public Zutat createZutat(Zutat zutat) {
        // Keine Notwendigkeit, die ID manuell zu generieren, da sie automatisch generiert wird
        // Setzen der eindeutigen ZutatID
        // zutat.setZutatID(generateZutatID());

        // Speichert die übergebene Zutat
        zutatRepository.save(zutat);

        // Gibt die erstellte Zutat zurück
        return zutat;
    }

    // Methode zur Abfrage aller Zutaten in der Datenbank
    public List<Zutat> getAllZutat() {
        return zutatRepository.findAll(); // Ruft alle Zutaten ab
    }

    // Methode zur Suche einer Zutat anhand des Namens
    public List<Zutat> searchByName(String zutatname) {
        // Ruft eine benutzerdefinierte Suche nach Zutaten anhand des Zutatnamens ab
        return zutatRepository.findZutatByNameContaining(zutatname);
    }
}
