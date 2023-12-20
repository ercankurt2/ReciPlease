package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Zutat;
import de.htwberlin.reciplease.repository.ZutatRepository;
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
class ZutatServiceTest {

    // Erstellt ein Mock-Objekt für das ZutatRepository, um es im Test zu verwenden
    @Mock
    ZutatRepository zutatRepository;

    // Injiziert das zuvor erstellte Mock-Objekt in den ZutatService, um es zu testen
    @InjectMocks
    ZutatService zutatService;

    // Testet die Methode createZutat im ZutatService
    @Test
    void shouldCreateZutat() {
        // Erstellt ein Beispiel-Zutat-Objekt
        Zutat zutat = new Zutat();
        zutat.setName("Tomate");

        // Konfigurieren des Mock-Objekts, um eine Zutat mit einer ID zurückzugeben
        when(zutatRepository.save(any(Zutat.class))).thenAnswer(invocation -> {
            Zutat savedZutat = invocation.getArgument(0, Zutat.class);
            savedZutat.setZutatID(1); // Setzen einer Dummy-ID
            return savedZutat;
        });

        // Ruft die Methode createZutat im ZutatService auf und speichert die erstellte Zutat
        Zutat createdZutat = this.zutatService.createZutat(zutat);

        // Überprüft, ob die erstellte Zutat den erwarteten Namen hat
        assertThat(createdZutat.getName()).isEqualTo(zutat.getName());

        // Überprüft, ob die erstellte Zutat eine ZutatID hat
        assertThat(createdZutat.getZutatID()).isNotNull();

        // Mithilfe von ArgumentCaptor die übergebene Entität beim Aufruf von zutatRepository.save(...) erfassen
        ArgumentCaptor<Zutat> zutatArgumentCaptor = ArgumentCaptor.forClass(Zutat.class);
        verify(zutatRepository).save(zutatArgumentCaptor.capture());
        Zutat captorValue = zutatArgumentCaptor.getValue();

        // Überprüft, ob die erfasste Zutat eine ZutatID hat
        assertThat(captorValue.getZutatID()).isNotNull();
    }

    // Testet die Methode updateZutat im ZutatService
    @Test
    void shouldUpdateZutat() {
        // Erstellt ein Beispiel-Zutat-Objekt
        Zutat existingZutat = new Zutat();
        existingZutat.setZutatID(1);
        existingZutat.setName("Tomaten");
        existingZutat.setMenge(500.0f); // Setzt die Menge als Float
        existingZutat.setEinheit("Gramm");

        // Erstellt ein neues Zutat-Objekt mit den aktualisierten Daten
        Zutat newZutatDetails = new Zutat();
        newZutatDetails.setName("Kartoffeln");
        newZutatDetails.setMenge(1000.0f); // Setzt die Menge als Float
        newZutatDetails.setEinheit("Gramm");

        // Konfigurieren des Mock-Objekts, um die existierende Zutat zurückzugeben, wenn findById aufgerufen wird
        when(zutatRepository.findById(1)).thenReturn(Optional.of(existingZutat));

        // Konfigurieren des Mock-Objekts, um die aktualisierte Zutat zurückzugeben, wenn save aufgerufen wird
        when(zutatRepository.save(any(Zutat.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ruft die Methode updateZutat im ZutatService auf und speichert die aktualisierte Zutat
        Zutat updatedZutat = this.zutatService.updateZutat(1, newZutatDetails);

        // Überprüft, ob die aktualisierte Zutat die neuen Daten hat
        assertThat(updatedZutat.getName()).isEqualTo(newZutatDetails.getName());
        assertThat(updatedZutat.getMenge()).isEqualTo(newZutatDetails.getMenge());
        assertThat(updatedZutat.getEinheit()).isEqualTo(newZutatDetails.getEinheit());

        // Mithilfe von ArgumentCaptor die übergebene Entität beim Aufruf von zutatRepository.save(...) erfassen
        ArgumentCaptor<Zutat> zutatArgumentCaptor = ArgumentCaptor.forClass(Zutat.class);
        verify(zutatRepository).save(zutatArgumentCaptor.capture());
        Zutat captorValue = zutatArgumentCaptor.getValue();

        // Überprüft, ob die erfasste Zutat die neuen Daten hat
        assertThat(captorValue.getName()).isEqualTo(newZutatDetails.getName());
        assertThat(captorValue.getMenge()).isEqualTo(newZutatDetails.getMenge());
        assertThat(captorValue.getEinheit()).isEqualTo(newZutatDetails.getEinheit());
    }

    // Testet die Methode deleteZutat im ZutatService
    @Test
    void shouldDeleteZutat() {
        // Erstellt ein Beispiel-Zutat-Objekt
        Zutat existingZutat = new Zutat();
        existingZutat.setZutatID(1);
        existingZutat.setName("Tomaten");
        existingZutat.setMenge(500.0f); // Setzt die Menge als Float
        existingZutat.setEinheit("Gramm");

        // Konfigurieren des Mock-Objekts, um die existierende Zutat zurückzugeben, wenn findById aufgerufen wird
        when(zutatRepository.findById(1)).thenReturn(Optional.of(existingZutat));

        // Ruft die Methode deleteZutat im ZutatService auf und speichert die gelöschte Zutat
        Zutat deletedZutat = this.zutatService.deleteZutat(1);

        // Überprüft, ob die gelöschte Zutat die erwarteten Daten hat
        assertThat(deletedZutat.getName()).isEqualTo(existingZutat.getName());
        assertThat(deletedZutat.getMenge()).isEqualTo(existingZutat.getMenge());
        assertThat(deletedZutat.getEinheit()).isEqualTo(existingZutat.getEinheit());

        // Überprüft, ob die Methode delete des zutatRepository aufgerufen wurde
        verify(zutatRepository).delete(existingZutat);
    }
}
