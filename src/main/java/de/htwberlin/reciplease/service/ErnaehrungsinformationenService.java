package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Ernaehrungsinformationen;
import de.htwberlin.reciplease.repository.ErnaehrungsinformationenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service-Annotation kennzeichnet diese Klasse als einen Spring-Service,
// der die Geschäftslogik der Anwendung implementiert
@Service
@RequiredArgsConstructor // Lombok-Anmerkung, die einen Konstruktor erstellt
public class ErnaehrungsinformationenService {

    // Injektion des ErnaehrungsinformationenRepository, um auf Datenbankoperationen zuzugreifen
    private final ErnaehrungsinformationenRepository ernaehrungsinformationenRepository;

    // Methode zur Generierung einer eindeutigen ErnaehrungsinformationenID
    public Integer generateErnaehrungsinformationenID() {
        // Ruft die Methode count() des ErnaehrungsinformationenRepository auf, um die Anzahl der Ernaehrungsinformationen in der Datenbank zu erhalten
        Integer count = Math.toIntExact(ernaehrungsinformationenRepository.count());
        // Erhöht die Anzahl der Ernaehrungsinformationen um 1, um eine eindeutige ErnaehrungsinformationenID zu generieren
        return count + 1;
    }

    // Methode zur Erstellung einer neuen Ernährungsinformation in der Datenbank
    public Ernaehrungsinformationen createErnaehrungsinformationen(Ernaehrungsinformationen ernaehrungsinformationen) {
        // Keine Notwendigkeit, die ID manuell zu generieren, da sie automatisch generiert wird
        // Setzen der eindeutigen ErnaehrungsinformationenID
        // ernaehrungsinformationen.setErnaehrungsinformationenID(generateErnaehrungsinformationenID());

        // Speichert die übergebene Ernährungsinformation
        ernaehrungsinformationenRepository.save(ernaehrungsinformationen);

        // Gibt die erstellte Ernährungsinformation zurück
        return ernaehrungsinformationen;
    }

    // Methode zur Abfrage aller Ernährungsinformationen in der Datenbank
    public List<Ernaehrungsinformationen> getAllErnaehrungsinformationen() {
        return ernaehrungsinformationenRepository.findAll(); // Ruft alle Ernährungsinformationen ab
    }

    // Methode zur Suche einer Ernährungsinformation anhand der ID
    public List<Ernaehrungsinformationen> searchByID(Integer ernaehrungsinformationenID) {
        // Ruft eine benutzerdefinierte Suche nach Ernährungsinformationen anhand der ID ab
        return ernaehrungsinformationenRepository.findErnaehrungsinformationenByErnaehrungsinformationenID(ernaehrungsinformationenID);
    }
}
