package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Rezept;
import de.htwberlin.reciplease.repository.RezeptRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

// Ermöglicht die Verwendung von Mockito-Annotationen in den Tests
@ExtendWith(MockitoExtension.class)
class RezeptServiceTest {

    // Erstellt ein Mock-Objekt für das RezeptRepository, um es im Test zu verwende
    @Mock
    RezeptRepository rezeptRepository;

    // Injiziert das zuvor erstellte Mock-Objekt in den RezeptService, um es zu testen
    @InjectMocks
    RezeptService rezeptService;

    @Test
    void shouldCreateRezept() {
        // Erstellt ein Beispiel-Rezeptobjekt
        Rezept rezept = new Rezept();
        rezept.setTitel("Tomatensuppe");

        // Ruft die Methode createRezept im RezeptService auf und speichert das erstellte Rezept
        Rezept createdRezept = this.rezeptService.createRezept(rezept);

        // Überprüft, ob das erstellte Rezept den erwarteten Titel hat
        assertThat(createdRezept.getTitel()).isEqualTo(rezept.getTitel());

        // Überprüft, ob das erstellte Rezept eine RezeptID hat
        // assertThat(createdRezept.getRezeptID()).isNotNull();

        // Mithilfe von ArgumentCaptor die übergebene Entität beim Aufruf von rezeptRepository.save(...) erfassen
        ArgumentCaptor<Rezept> rezeptArgumentCaptor = ArgumentCaptor.forClass(Rezept.class);
        verify(rezeptRepository).save(rezeptArgumentCaptor.capture());
        Rezept captorValue = rezeptArgumentCaptor.getValue();

        // Überprüft, ob das erfasste Rezept eine RezeptID hat
        // assertThat(captorValue.getRezeptID()).isNotNull();
    }
}
