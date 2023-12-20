package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Zutat;
import de.htwberlin.reciplease.repository.ZutatRepository;
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
}
