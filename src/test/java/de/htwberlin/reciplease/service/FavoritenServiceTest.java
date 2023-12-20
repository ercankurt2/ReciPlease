package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Favoriten;
import de.htwberlin.reciplease.model.Benutzer;
import de.htwberlin.reciplease.model.Rezept;
import de.htwberlin.reciplease.repository.FavoritenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Ermöglicht die Verwendung von Mockito-Annotationen in den Tests
@ExtendWith(MockitoExtension.class)
class FavoritenServiceTest {

    // Erstellt ein Mock-Objekt für das FavoritenRepository, um es im Test zu verwenden
    @Mock
    FavoritenRepository favoritenRepository;

    // Injiziert das zuvor erstellte Mock-Objekt in den FavoritenService, um es zu testen
    @InjectMocks
    FavoritenService favoritenService;

    // Testet die Methode createFavoriten im FavoritenService
    @Test
    void shouldCreateFavoriten() {
        // Erstellt ein Beispiel-Favoriten-Objekt
        Favoriten favoriten = new Favoriten();
        favoriten.setFavoritenID(1);

        // Konfigurieren des Mock-Objekts, um einen Favoriten mit einer ID zurückzugeben
        when(favoritenRepository.save(any(Favoriten.class))).thenAnswer(invocation -> {
            Favoriten savedFavoriten = invocation.getArgument(0, Favoriten.class);
            savedFavoriten.setFavoritenID(1); // Setzen einer Dummy-ID
            return savedFavoriten;
        });

        // Ruft die Methode createFavoriten im FavoritenService auf und speichert den erstellten Favoriten
        Favoriten createdFavoriten = this.favoritenService.createFavoriten(favoriten);

        // Überprüft, ob der erstellte Favorit eine FavoritenID hat
        assertThat(createdFavoriten.getFavoritenID()).isNotNull();

        // Überprüft, ob der erstellte Favorit die erwartete FavoritenID hat
        assertThat(createdFavoriten.getFavoritenID()).isEqualTo(favoriten.getFavoritenID());

        // Mithilfe von ArgumentCaptor die übergebene Entität beim Aufruf von favoritenRepository.save(...) erfassen
        ArgumentCaptor<Favoriten> favoritenArgumentCaptor = ArgumentCaptor.forClass(Favoriten.class);
        verify(favoritenRepository).save(favoritenArgumentCaptor.capture());
        Favoriten captorValue = favoritenArgumentCaptor.getValue();

        // Überprüft, ob das erfasste Favoriten eine FavoritenID hat
        assertThat(captorValue.getFavoritenID()).isNotNull();
    }

    @Test
    void shouldUpdateFavoriten() {
        // Erstellt ein Beispiel-Benutzer-Objekt
        Benutzer benutzerErwin = new Benutzer();
        benutzerErwin.setBenutzername("Erwin");

        // Erstellt ein anderes Beispiel-Benutzer-Objekt
        Benutzer benutzerErika = new Benutzer();
        benutzerErika.setBenutzername("Erika");

        // Erstellt ein Beispiel-Rezept-Objekt
        Rezept rezeptTomatensuppe = new Rezept();
        rezeptTomatensuppe.setTitel("Tomatensuppe");

        // Erstellt ein anderes Beispiel-Rezept-Objekt
        Rezept rezeptKuerbissuppe = new Rezept();
        rezeptKuerbissuppe.setTitel("Kürbissuppe");

        // Erstellt ein Beispiel-Favoriten-Objekt
        Favoriten existingFavoriten = new Favoriten();
        existingFavoriten.setFavoritenID(1);
        existingFavoriten.setBenutzer(benutzerErwin);
        existingFavoriten.setRezept(rezeptTomatensuppe);

        // Erstellt ein neues Favoriten-Objekt mit den aktualisierten Daten
        Favoriten newFavoritenDetails = new Favoriten();
        newFavoritenDetails.setBenutzer(benutzerErika);
        newFavoritenDetails.setRezept(rezeptKuerbissuppe);

        // Konfigurieren des Mock-Objekts, um den existierenden Favoriten zurückzugeben, wenn findById aufgerufen wird
        when(favoritenRepository.findById(1)).thenReturn(Optional.of(existingFavoriten));

        // Konfigurieren des Mock-Objekts, um den aktualisierten Favoriten zurückzugeben, wenn save aufgerufen wird
        when(favoritenRepository.save(any(Favoriten.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ruft die Methode updateFavoriten im FavoritenService auf und speichert den aktualisierten Favoriten
        Favoriten updatedFavoriten = this.favoritenService.updateFavoriten(1, newFavoritenDetails);

        // Überprüft, ob der aktualisierte Favorit den neuen Benutzer und das neue Rezept hat
        assertThat(updatedFavoriten.getBenutzer()).isEqualTo(newFavoritenDetails.getBenutzer());
        assertThat(updatedFavoriten.getRezept()).isEqualTo(newFavoritenDetails.getRezept());

        // Mithilfe von ArgumentCaptor die übergebene Entität beim Aufruf von favoritenRepository.save(...) erfassen
        ArgumentCaptor<Favoriten> favoritenArgumentCaptor = ArgumentCaptor.forClass(Favoriten.class);
        verify(favoritenRepository).save(favoritenArgumentCaptor.capture());
        Favoriten captorValue = favoritenArgumentCaptor.getValue();

        // Überprüft, ob der erfasste Favorit den neuen Benutzer und das neue Rezept hat
        assertThat(captorValue.getBenutzer()).isEqualTo(newFavoritenDetails.getBenutzer());
        assertThat(captorValue.getRezept()).isEqualTo(newFavoritenDetails.getRezept());
    }
}
