package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Benutzer;
import de.htwberlin.reciplease.repository.BenutzerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Ermöglicht die Verwendung von Mockito-Annotationen in den Tests
@ExtendWith(MockitoExtension.class)
class BenutzerServiceTest {

    // Erstellt ein Mock-Objekt für das BenutzerRepository, um es im Test zu verwenden
    @Mock
    BenutzerRepository benutzerRepository;

    // Injiziert das zuvor erstellte Mock-Objekt in den BenutzerService, um es zu testen
    @InjectMocks
    BenutzerService benutzerService;

    // Testet die Methode createBenutzer im BenutzerService
    @Test
    void shouldCreateBenutzer() {
        // Erstellt ein Beispiel-Benutzer-Objekt
        Benutzer benutzer = new Benutzer();
        benutzer.setBenutzername("Erwin");

        // Konfigurieren des Mock-Objekts, um einen Benutzer mit einer ID zurückzugeben
        when(benutzerRepository.save(any(Benutzer.class))).thenAnswer(invocation -> {
            Benutzer savedBenutzer = invocation.getArgument(0, Benutzer.class);
            savedBenutzer.setBenutzerID(1); // Setzen einer Dummy-ID
            return savedBenutzer;
        });

        // Ruft die Methode createBenutzer im BenutzerService auf und speichert den erstellten Benutzer
        Benutzer createdBenutzer = this.benutzerService.createBenutzer(benutzer);

        // Überprüft, ob der erstellte Benutzer den erwarteten Benutzernamen hat
        assertThat(createdBenutzer.getBenutzername()).isEqualTo(benutzer.getBenutzername());

        // Überprüft, ob der erstellte Benutzer eine BenutzerID hat
        assertThat(createdBenutzer.getBenutzerID()).isNotNull();

        // Mithilfe von ArgumentCaptor die übergebene Entität beim Aufruf von benutzerRepository.save(...) erfassen
        ArgumentCaptor<Benutzer> benutzerArgumentCaptor = ArgumentCaptor.forClass(Benutzer.class);
        verify(benutzerRepository).save(benutzerArgumentCaptor.capture());
        Benutzer captorValue = benutzerArgumentCaptor.getValue();

        // Überprüft, ob das erfasste Benutzer eine BenutzerID hat
        assertThat(captorValue.getBenutzerID()).isNotNull();
    }
}
