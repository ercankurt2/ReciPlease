package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Rezept;
import de.htwberlin.reciplease.repository.RezeptRepository;
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
class RezeptServiceTest {

    // Erstellt ein Mock-Objekt für das RezeptRepository, um es im Test zu verwenden
    @Mock
    RezeptRepository rezeptRepository;

    // Injiziert das zuvor erstellte Mock-Objekt in den RezeptService, um es zu testen
    @InjectMocks
    RezeptService rezeptService;

    // Testet die Methode createRezept im RezeptService
    @Test
    void shouldCreateRezept() {
        // Erstellt ein Beispiel-Rezept-Objekt
        Rezept rezept = new Rezept();
        rezept.setTitel("Tomatensuppe");

        // Konfigurieren des Mock-Objekts, um ein Rezept mit einer ID zurückzugeben
        when(rezeptRepository.save(any(Rezept.class))).thenAnswer(invocation -> {
            Rezept savedRezept = invocation.getArgument(0, Rezept.class);
            savedRezept.setRezeptID(1); // Setzen einer Dummy-ID
            return savedRezept;
        });

        // Ruft die Methode createRezept im RezeptService auf und speichert das erstellte Rezept
        Rezept createdRezept = this.rezeptService.createRezept(rezept);

        // Überprüft, ob das erstellte Rezept den erwarteten Titel hat
        assertThat(createdRezept.getTitel()).isEqualTo(rezept.getTitel());

        // Überprüft, ob das erstellte Rezept eine RezeptID hat
        assertThat(createdRezept.getRezeptID()).isNotNull();

        // Mithilfe von ArgumentCaptor die übergebene Entität beim Aufruf von rezeptRepository.save(...) erfassen
        ArgumentCaptor<Rezept> rezeptArgumentCaptor = ArgumentCaptor.forClass(Rezept.class);
        verify(rezeptRepository).save(rezeptArgumentCaptor.capture());
        Rezept captorValue = rezeptArgumentCaptor.getValue();

        // Überprüft, ob das erfasste Rezept eine RezeptID hat
        assertThat(captorValue.getRezeptID()).isNotNull();
    }

    @Test
    void shouldUpdateRezept() {
        // Erstellt ein Beispiel-Rezept-Objekt
        Rezept existingRezept = new Rezept();
        existingRezept.setRezeptID(1);
        existingRezept.setTitel("Alte Tomatensuppe");

        // Erstellt ein neues Rezept-Objekt mit den aktualisierten Daten
        Rezept newRezeptDetails = new Rezept();
        newRezeptDetails.setTitel("Neue Tomatensuppe");

        // Konfigurieren des Mock-Objekts, um das existierende Rezept zurückzugeben, wenn findById aufgerufen wird
        when(rezeptRepository.findById(1)).thenReturn(Optional.of(existingRezept));

        // Konfigurieren des Mock-Objekts, um das aktualisierte Rezept zurückzugeben, wenn save aufgerufen wird
        when(rezeptRepository.save(any(Rezept.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ruft die Methode updateRezept im RezeptService auf und speichert das aktualisierte Rezept
        Rezept updatedRezept = this.rezeptService.updateRezept(1, newRezeptDetails);

        // Überprüft, ob das aktualisierte Rezept den neuen Titel hat
        assertThat(updatedRezept.getTitel()).isEqualTo(newRezeptDetails.getTitel());

        // Mithilfe von ArgumentCaptor die übergebene Entität beim Aufruf von rezeptRepository.save(...) erfassen
        ArgumentCaptor<Rezept> rezeptArgumentCaptor = ArgumentCaptor.forClass(Rezept.class);
        verify(rezeptRepository).save(rezeptArgumentCaptor.capture());
        Rezept captorValue = rezeptArgumentCaptor.getValue();

        // Überprüft, ob das erfasste Rezept den neuen Titel hat
        assertThat(captorValue.getTitel()).isEqualTo(newRezeptDetails.getTitel());
    }
}
