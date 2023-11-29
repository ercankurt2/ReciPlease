package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Kategorie;
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

    // Methode zur Abfrage aller Kategorien in der Datenbank
    public List<Kategorie> getAllKategorie() {
        return kategorieRepository.findAll(); // Ruft alle Kategorien ab
    }

    // Methode zur Suche einer Kategorie anhand des Namens
    public List<Kategorie> searchByName(String kategoriename) {
        // Ruft eine benutzerdefinierte Suche nach Kategorien anhand des Kategoriennamens ab
        return kategorieRepository.findKategorieByNameContaining(kategoriename);
    }
}
