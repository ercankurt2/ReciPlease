package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Favoriten;
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

    // Methode zur Abfrage aller Favoriten in der Datenbank
    public List<Favoriten> getAllFavoriten() {
        return favoritenRepository.findAll(); // Ruft alle Favoriten ab
    }

    // Methode zur Suche eines Favoriten anhand der ID
    public List<Favoriten> searchByID(Integer favoritenID) {
        // Ruft eine benutzerdefinierte Suche nach Favoriten anhand der ID ab
        return favoritenRepository.findFavoritenByFavoritenID(favoritenID);
    }
}
