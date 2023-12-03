package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Kategorie;
import de.htwberlin.reciplease.model.Rezept;
import de.htwberlin.reciplease.repository.KategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service-Annotation kennzeichnet diese Klasse als einen Spring-Service,
// der die Geschäftslogik der Anwendung implementiert
@Service
@RequiredArgsConstructor // Lombok-Anmerkung, die einen Konstruktor erstellt
public class KategorieService {

    // Injektion des KategorieRepository, um auf Datenbankoperationen zuzugreifen
    private final KategorieRepository kategorieRepository;

    // Methode zur Generierung einer eindeutigen KategorieID
    public Integer generateKategorieID() {
        // Ruft die Methode count() des KategorieRepository auf, um die Anzahl der Kategorien in der Datenbank zu erhalten
        Integer count = Math.toIntExact(kategorieRepository.count());
        // Erhöht die Anzahl der Kategorien um 1, um eine eindeutige KategorieID zu generieren
        return count + 1;
    }

    // Methode zur Erstellung einer neuen Kategorie in der Datenbank
    public Kategorie createKategorie(Kategorie kategorie) {
        // Keine Notwendigkeit, die ID manuell zu generieren, da sie automatisch generiert wird
        // Setzen der eindeutigen KategorieID
        // kategorie.setKategorieID(generateKategorieID());

        // Speichert die übergebene Kategorie
        kategorieRepository.save(kategorie);

        // Gibt die erstellte Kategorie zurück
        return kategorie;
    }
    // Methode zur Aktualisierung von Kategorie in der Datenbank
    public Kategorie updateKategorie(Integer id, Kategorie kategorieDetails) {
        // Ruft die Kategorie anhand ihrer ID aus der Datenbank ab
        Kategorie kategorieToUpdate = kategorieRepository.findById(id).orElse(null);

        if (kategorieToUpdate != null) {
            // Aktualisieren der Daten der Kategorie mit den Details aus dem übergebenen Kategorie-Objekt
            kategorieToUpdate.setName(kategorieDetails.getName());

            // Speichern der aktualisierten Kategorie in der Datenbank
            kategorieRepository.save(kategorieToUpdate);

            // Rückgabe der aktualisierten Kategorie
            return kategorieToUpdate;
        } else {
            // Wenn die Kategorie nicht gefunden wird, wird eine geeignete Ausnahme geworfen oder null zurückgegeben
            // Option: eine benutzerdefinierte Ausnahme werfen
            // throw new EntityNotFoundException("Kategorie mit ID " + id + " nicht gefunden.");

            // Option: null zurückgeben
            return null;
        }
    }

    // Methode zur Löschung einer Kategorie aus der Datenbank
    public Kategorie deleteKategorie(Integer id) {
        // Ruft die Kategorie anhand ihrer ID aus der Datenbank ab
        Kategorie kategorieToDelete = kategorieRepository.findById(id).orElse(null);

        if (kategorieToDelete != null) {
            // Löscht die Kategorie aus der Datenbank
            kategorieRepository.delete(kategorieToDelete);

            // Rückgabe der gelöschten Kategorie
            return kategorieToDelete;
        } else {
            // Wenn die Kategorie nicht gefunden wird, wird eine geeignete Ausnahme geworfen oder null zurückgegeben
            // Option: eine benutzerdefinierte Ausnahme werfen
            // throw new EntityNotFoundException("Kategorie mit ID " + id + " nicht gefunden.");

            // Option: null zurückgeben
            return null;
        }
    }
    // Methode zur Abfrage aller Kategorien in der Datenbank
    public List<Kategorie> getAllKategorie() {
        return kategorieRepository.findAll(); // Ruft alle Kategorien ab
    }

    // Methode zur Suche einer Kategorie anhand des Namens
    public List<Kategorie> searchByName(String kategoriename) {
        // Ruft eine benutzerdefinierte Suche nach Kategorien anhand des Kategoriennamens ab
        return kategorieRepository.findKategorieByNameContaining(kategoriename);
    }

    // Methode, um eine Kategorie anhand ihrer ID zu erhalten
    public Kategorie getKategorieById(Integer id) {
        // Verwendung von findById, um eine Kategorie zu finden oder null zurückzugeben
        return kategorieRepository.findById(id).orElse(null);
    }
}
