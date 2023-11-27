package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Benutzer;
import de.htwberlin.reciplease.repository.BenutzerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service-Annotation kennzeichnet diese Klasse als einen Spring-Service,
// der die Geschäftslogik der Anwendung implementiert
@Service
@RequiredArgsConstructor // Lombok-Anmerkung, die einen Konstruktor erstellt
public class BenutzerService {

    // Injektion des BenutzerRepository, um auf Datenbankoperationen zuzugreifen
    private final BenutzerRepository benutzerRepository;

    // Methode zur Generierung einer eindeutigen BenutzerID
    public Integer generateBenutzerID() {
        // Ruft die Methode count() des BenutzerRepository auf, um die Anzahl der Benutzer in der Datenbank zu erhalten
        Integer count = Math.toIntExact(benutzerRepository.count());
        // Erhöht die Anzahl der Benutzer um 1, um eine eindeutige BenutzerID zu generieren
        return count + 1;
    }

    // Methode zur Erstellung eines neuen Benutzers in der Datenbank
    public Benutzer createBenutzer(Benutzer benutzer) {
        // Setzen der eindeutigen BenutzerID
        benutzer.setBenutzerID(generateBenutzerID());

        // Speichert den übergebenen Benutzer
        benutzerRepository.save(benutzer);

        // Gibt den erstellten Benutzer zurück
        return benutzer;
    }

    // Methode zur Abfrage aller Benutzer in der Datenbank
    public List<Benutzer> getAllBenutzer() {
        return benutzerRepository.findAll(); // Ruft alle Benutzer ab
    }

    // Methode zur Suche eines Benutzers anhand des Namens
    public List<Benutzer> searchByName(String benutzername) {
        // Ruft eine benutzerdefinierte Suche nach Benutzern anhand des Benutzernamens ab
        return benutzerRepository.findBenutzerByBenutzernameContaining(benutzername);
    }
}
