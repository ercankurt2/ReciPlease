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
        // Keine Notwendigkeit, die ID manuell zu generieren, da sie automatisch generiert wird
        // Setzen der eindeutigen BenutzerID
        // benutzer.setBenutzerID(generateBenutzerID());

        // Speichert den übergebenen Benutzer
        benutzerRepository.save(benutzer);

        // Gibt den erstellten Benutzer zurück
        return benutzer;
    }

    // Methode zur Aktualisierung eines Benutzers in der Datenbank
    public Benutzer updateBenutzer(Integer id, Benutzer benutzerDetails) {
        // Ruft den Benutzer anhand seiner ID aus der Datenbank ab
        Benutzer benutzerToUpdate = benutzerRepository.findById(id).orElse(null);

        if (benutzerToUpdate != null) {
            // Aktualisieren der Daten des Benutzers mit den Details aus dem übergebenen Benutzer-Objekt
            benutzerToUpdate.setBenutzername(benutzerDetails.getBenutzername());
            benutzerToUpdate.setEmail(benutzerDetails.getEmail());
            benutzerToUpdate.setPasswort(benutzerDetails.getPasswort());

            // Speichern des aktualisierten Benutzers in der Datenbank
            benutzerRepository.save(benutzerToUpdate);

            // Rückgabe des aktualisierten Benutzers
            return benutzerToUpdate;
        } else {
            // Wenn der Benutzer nicht gefunden wird, wird eine geeignete Ausnahme geworfen oder null zurückgegeben
            // Option: eine benutzerdefinierte Ausnahme werfen
            // throw new EntityNotFoundException("Benutzer mit ID " + id + " nicht gefunden.");

            // Option: null zurückgeben
            return null;
        }
    }

    // Methode zur Löschung eines Benutzers aus der Datenbank
    public Benutzer deleteBenutzer(Integer id) {
        // Ruft den Benutzer anhand seiner ID aus der Datenbank ab
        Benutzer benutzerToDelete = benutzerRepository.findById(id).orElse(null);

        if (benutzerToDelete != null) {
            // Löscht den Benutzer aus der Datenbank
            benutzerRepository.delete(benutzerToDelete);

            // Rückgabe des gelöschten Benutzers
            return benutzerToDelete;
        } else {
            // Wenn der Benutzer nicht gefunden wird, wird eine geeignete Ausnahme geworfen oder null zurückgegeben
            // Option: eine benutzerdefinierte Ausnahme werfen
            // throw new EntityNotFoundException("Benutzer mit ID " + id + " nicht gefunden.");

            // Option: null zurückgeben
            return null;
        }
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

    // Methode, um einen Benutzer anhand seiner ID zu erhalten
    public Benutzer getBenutzerById(Integer id) {
        // Verwendung von findById, um einen Benutzer zu finden oder null zurückzugeben
        return benutzerRepository.findById(id).orElse(null);
    }
}
