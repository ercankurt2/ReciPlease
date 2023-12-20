package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Kategorie;
import de.htwberlin.reciplease.repository.KategorieRepository;
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
class KategorieServiceTest {

    // Erstellt ein Mock-Objekt für das KategorieRepository, um es im Test zu verwenden
    @Mock
    KategorieRepository kategorieRepository;

    // Injiziert das zuvor erstellte Mock-Objekt in den KategorieService, um es zu testen
    @InjectMocks
    KategorieService kategorieService;

    // Testet die Methode createKategorie im KategorieService
    @Test
    void shouldCreateKategorie() {
        // Erstellt ein Beispiel-Kategorie-Objekt
        Kategorie kategorie = new Kategorie();
        kategorie.setName("Suppen");

        // Konfigurieren des Mock-Objekts, um eine Kategorie mit einer ID zurückzugeben
        when(kategorieRepository.save(any(Kategorie.class))).thenAnswer(invocation -> {
            Kategorie savedKategorie = invocation.getArgument(0, Kategorie.class);
            savedKategorie.setKategorieID(1); // Setzen einer Dummy-ID
            return savedKategorie;
        });

        // Ruft die Methode createKategorie im KategorieService auf und speichert die erstellte Kategorie
        Kategorie createdKategorie = this.kategorieService.createKategorie(kategorie);

        // Überprüft, ob die erstellte Kategorie den erwarteten Namen hat
        assertThat(createdKategorie.getName()).isEqualTo(kategorie.getName());

        // Überprüft, ob die erstellte Kategorie eine KategorieID hat
        assertThat(createdKategorie.getKategorieID()).isNotNull();

        // Mithilfe von ArgumentCaptor die übergebene Entität beim Aufruf von kategorieRepository.save(...) erfassen
        ArgumentCaptor<Kategorie> kategorieArgumentCaptor = ArgumentCaptor.forClass(Kategorie.class);
        verify(kategorieRepository).save(kategorieArgumentCaptor.capture());
        Kategorie captorValue = kategorieArgumentCaptor.getValue();

        // Überprüft, ob die erfasste Kategorie eine KategorieID hat
        assertThat(captorValue.getKategorieID()).isNotNull();
    }

    @Test
    void shouldUpdateKategorie() {
        // Erstellt ein Beispiel-Kategorie-Objekt
        Kategorie existingKategorie = new Kategorie();
        existingKategorie.setKategorieID(1);
        existingKategorie.setName("Suppen");

        // Erstellt ein neues Kategorie-Objekt mit den aktualisierten Daten
        Kategorie newKategorieDetails = new Kategorie();
        newKategorieDetails.setName("Salate");

        // Konfigurieren des Mock-Objekts, um die existierende Kategorie zurückzugeben, wenn findById aufgerufen wird
        when(kategorieRepository.findById(1)).thenReturn(Optional.of(existingKategorie));

        // Konfigurieren des Mock-Objekts, um die aktualisierte Kategorie zurückzugeben, wenn save aufgerufen wird
        when(kategorieRepository.save(any(Kategorie.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ruft die Methode updateKategorie im KategorieService auf und speichert die aktualisierte Kategorie
        Kategorie updatedKategorie = this.kategorieService.updateKategorie(1, newKategorieDetails);

        // Überprüft, ob die aktualisierte Kategorie den neuen Namen hat
        assertThat(updatedKategorie.getName()).isEqualTo(newKategorieDetails.getName());

        // Mithilfe von ArgumentCaptor die übergebene Entität beim Aufruf von kategorieRepository.save(...) erfassen
        ArgumentCaptor<Kategorie> kategorieArgumentCaptor = ArgumentCaptor.forClass(Kategorie.class);
        verify(kategorieRepository).save(kategorieArgumentCaptor.capture());
        Kategorie captorValue = kategorieArgumentCaptor.getValue();

        // Überprüft, ob die erfasste Kategorie den neuen Namen hat
        assertThat(captorValue.getName()).isEqualTo(newKategorieDetails.getName());
    }
}
