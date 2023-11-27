package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.repository.BenutzerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
