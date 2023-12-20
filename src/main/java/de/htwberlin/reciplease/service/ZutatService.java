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

    // Methode zur Aktualisierung von Zutat in der Datenbank
    public Zutat updateZutat(Integer id, Zutat zutatDetails) {
        // Ruft die Zutat anhand ihrer ID aus der Datenbank ab
        Zutat zutatToUpdate = zutatRepository.findById(id).orElse(null);

        if (zutatToUpdate != null) {
            // Aktualisieren der Daten einer Zutat mit den Details aus dem übergebenen Zutat-Objekt
            zutatToUpdate.setName(zutatDetails.getName());
            zutatToUpdate.setMenge(zutatDetails.getMenge());
            zutatToUpdate.setEinheit(zutatDetails.getEinheit());

            // Speichern der aktualisierten Zutat in der Datenbank
            zutatRepository.save(zutatToUpdate);

            // Rückgabe der aktualisierten Zutat
            return zutatToUpdate;
        } else {
            // Wenn die Zutat nicht gefunden wird, wird eine geeignete Ausnahme geworfen oder null zurückgegeben
            // Option: eine benutzerdefinierte Ausnahme werfen
            // throw new EntityNotFoundException("Zutat mit ID " + id + " nicht gefunden.");

            // Option: null zurückgeben
            return null;
        }
    }

    // Methode zur Löschung einer Zutat aus der Datenbank
    public Zutat deleteZutat(Integer id) {
        // Ruft die Zutat anhand ihrer ID aus der Datenbank ab
        Zutat zutatToDelete = zutatRepository.findById(id).orElse(null);

        if (zutatToDelete != null) {
            // Löscht die Zutat aus der Datenbank
            zutatRepository.delete(zutatToDelete);

            // Rückgabe der gelöschten Zutat
            return zutatToDelete;
        } else {
            // Wenn die Zutat nicht gefunden wird, wird eine geeignete Ausnahme geworfen oder null zurückgegeben
            // Option: eine benutzerdefinierte Ausnahme werfen
            // throw new EntityNotFoundException("Zutat mit ID " + id + " nicht gefunden.");

            // Option: null zurückgeben
            return null;
        }
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

    // Methode, um eine Zutat anhand ihrer ID zu erhalten
    public Zutat getZutatById(Integer id) {
        // Verwendung von findById, um eine Zutat zu finden oder null zurückzugeben
        return zutatRepository.findById(id).orElse(null);
    }
}
