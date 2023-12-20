package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Ernaehrungsinformationen;
import de.htwberlin.reciplease.repository.ErnaehrungsinformationenRepository;
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
class ErnaehrungsinformationenServiceTest {

    // Erstellt ein Mock-Objekt für das ErnaehrungsinformationenRepository, um es im Test zu verwenden
    @Mock
    ErnaehrungsinformationenRepository ernaehrungsinformationenRepository;

    // Injiziert das zuvor erstellte Mock-Objekt in den ErnaehrungsinformationenService, um es zu testen
    @InjectMocks
    ErnaehrungsinformationenService ernaehrungsinformationenService;

    // Testet die Methode createErnaehrungsinformationen im ErnaehrungsinformationenService
    @Test
    void shouldCreateErnaehrungsinformationen() {
        // Erstellt ein Beispiel-Ernaehrungsinformationen-Objekt
        Ernaehrungsinformationen ernaehrungsinformationen = new Ernaehrungsinformationen();
        ernaehrungsinformationen.setKalorien(100);

        // Konfigurieren des Mock-Objekts, um eine Ernaehrungsinformation mit einer ID zurückzugeben
        when(ernaehrungsinformationenRepository.save(any(Ernaehrungsinformationen.class))).thenAnswer(invocation -> {
            Ernaehrungsinformationen savedErnaehrungsinformationen = invocation.getArgument(0, Ernaehrungsinformationen.class);
            savedErnaehrungsinformationen.setErnaehrungsinformationenID(1); // Setzen einer Dummy-ID
            return savedErnaehrungsinformationen;
        });

        // Ruft die Methode createErnaehrungsinformationen im ErnaehrungsinformationenService auf und speichert die erstellte Ernaehrungsinformation
        Ernaehrungsinformationen createdErnaehrungsinformationen = this.ernaehrungsinformationenService.createErnaehrungsinformationen(ernaehrungsinformationen);

        // Überprüft, ob die erstellte Ernaehrungsinformation die erwarteten Kalorien hat
        assertThat(createdErnaehrungsinformationen.getKalorien()).isEqualTo(ernaehrungsinformationen.getKalorien());

        // Überprüft, ob die erstellte Ernaehrungsinformation eine ErnaehrungsinformationenID hat
        assertThat(createdErnaehrungsinformationen.getErnaehrungsinformationenID()).isNotNull();

        // Mithilfe von ArgumentCaptor die übergebene Entität beim Aufruf von ernaehrungsinformationenRepository.save(...) erfassen
        ArgumentCaptor<Ernaehrungsinformationen> ernaehrungsinformationenArgumentCaptor = ArgumentCaptor.forClass(Ernaehrungsinformationen.class);
        verify(ernaehrungsinformationenRepository).save(ernaehrungsinformationenArgumentCaptor.capture());
        Ernaehrungsinformationen captorValue = ernaehrungsinformationenArgumentCaptor.getValue();

        // Überprüft, ob die erfasste Ernaehrungsinformation eine ErnaehrungsinformationenID hat
        assertThat(captorValue.getErnaehrungsinformationenID()).isNotNull();
    }
}
