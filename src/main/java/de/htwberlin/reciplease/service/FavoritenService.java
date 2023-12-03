package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Favoriten;
import de.htwberlin.reciplease.model.Rezept;
import de.htwberlin.reciplease.repository.FavoritenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service-Annotation kennzeichnet diese Klasse als einen Spring-Service,
// der die Geschäftslogik der Anwendung implementiert
@Service
@RequiredArgsConstructor // Lombok-Anmerkung, die einen Konstruktor erstellt
public class FavoritenService {

    // Injektion des FavoritenRepository, um auf Datenbankoperationen zuzugreifen
    private final FavoritenRepository favoritenRepository;

    // Methode zur Generierung einer eindeutigen FavoritenID
    public Integer generateFavoritenID() {
        // Ruft die Methode count() des FavoritenRepository auf, um die Anzahl der Favoriten in der Datenbank zu erhalten
        Integer count = Math.toIntExact(favoritenRepository.count());
        // Erhöht die Anzahl der Favoriten um 1, um eine eindeutige FavoritenID zu generieren
        return count + 1;
    }

    // Methode zur Erstellung eines neuen Favoriten in der Datenbank
    public Favoriten createFavoriten(Favoriten favoriten) {
        // Keine Notwendigkeit, die ID manuell zu generieren, da sie automatisch generiert wird
        // Setzen der eindeutigen FavoritenID
        // favoriten.setFavoritenID(generateFavoritenID());

        // Speichert den übergebenen Favoriten
        favoritenRepository.save(favoriten);

        // Gibt den erstellten Favoriten zurück
        return favoriten;
    }

    // Methode zur Aktualisierung von Favoriten in der Datenbank
    public Favoriten updateFavoriten(Integer id, Favoriten favoritenDetails) {
        // Ruft  Favorit anhand seiner ID aus der Datenbank ab
        Favoriten favoritenToUpdate = favoritenRepository.findById(id).orElse(null);

        if (favoritenToUpdate != null) {
            // Aktualisieren der Daten von Favorit mit den Details aus dem übergebenen Favoriten-Objekt
            favoritenToUpdate.setBenutzer(favoritenDetails.getBenutzer());
            favoritenToUpdate.setRezept(favoritenDetails.getRezept());

            // Speichern der aktualisierten Favoriten in der Datenbank
            favoritenRepository.save(favoritenToUpdate);

            // Rückgabe der aktualisierten Favoriten
            return favoritenToUpdate;
        } else {
            // Wenn der Favorit nicht gefunden wird, wird eine geeignete Ausnahme geworfen oder null zurückgegeben
            // Option: eine benutzerdefinierte Ausnahme werfen
            // throw new EntityNotFoundException("Favorit mit ID " + id + " nicht gefunden.");

            // Option: null zurückgeben
            return null;
        }
    }

    // Methode zur Löschung eines Favoriten aus der Datenbank
    public Favoriten deleteFavoriten(Integer id) {
        // Ruft der Favorit anhand seiner ID aus der Datenbank ab
        Favoriten favoritenToDelete = favoritenRepository.findById(id).orElse(null);

        if (favoritenToDelete != null) {
            // Löscht der Favorit aus der Datenbank
            favoritenRepository.delete(favoritenToDelete);

            // Rückgabe des gelöschten Favorits
            return favoritenToDelete;
        } else {
            // Wenn der Favorit nicht gefunden wird, wird eine geeignete Ausnahme geworfen oder null zurückgegeben
            // Option: eine benutzerdefinierte Ausnahme werfen
            // throw new EntityNotFoundException("Favorit mit ID " + id + " nicht gefunden.");

            // Option: null zurückgeben
            return null;
        }
    }
    // Methode zur Abfrage aller Favoriten in der Datenbank
    public List<Favoriten> getAllFavoriten() {
        return favoritenRepository.findAll(); // Ruft alle Favoriten ab
    }

    // Methode zur Suche eines Favoriten anhand der ID
    public List<Favoriten> searchByID(Integer favoritenID) {
        // Ruft eine benutzerdefinierte Suche nach Favoriten anhand der ID ab
        return favoritenRepository.findFavoritenByFavoritenID(favoritenID);
    }
    // Methode, um einen Favoriten anhand seiner ID zu erhalten
    public Favoriten getFavoritenById(Integer id) {
        // Verwendung von findById, um einen Favoriten zu finden oder null zurückzugeben
        return favoritenRepository.findById(id).orElse(null);
    }
}}

