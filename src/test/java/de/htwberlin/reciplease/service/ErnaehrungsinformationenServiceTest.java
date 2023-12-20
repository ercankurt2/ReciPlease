package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Ernaehrungsinformationen;
import de.htwberlin.reciplease.repository.ErnaehrungsinformationenRepository;
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

    // Testet die Methode updateErnaehrungsinformationen im ErnaehrungsinformationenService
    @Test
    void shouldUpdateErnaehrungsinformationen() {
        // Erstellt ein Beispiel-Ernaehrungsinformationen-Objekt
        Ernaehrungsinformationen existingErnaehrungsinformationen = new Ernaehrungsinformationen();
        existingErnaehrungsinformationen.setErnaehrungsinformationenID(1);
        existingErnaehrungsinformationen.setKalorien(2000);
        existingErnaehrungsinformationen.setProtein(50);
        existingErnaehrungsinformationen.setKohlenhydrate(250);
        existingErnaehrungsinformationen.setFett(70);

        // Erstellt ein neues Ernaehrungsinformationen-Objekt mit den aktualisierten Daten
        Ernaehrungsinformationen newErnaehrungsinformationenDetails = new Ernaehrungsinformationen();
        newErnaehrungsinformationenDetails.setKalorien(1800);
        newErnaehrungsinformationenDetails.setProtein(60);
        newErnaehrungsinformationenDetails.setKohlenhydrate(200);
        newErnaehrungsinformationenDetails.setFett(60);

        // Konfigurieren des Mock-Objekts, um die existierenden Ernaehrungsinformationen zurückzugeben, wenn findById aufgerufen wird
        when(ernaehrungsinformationenRepository.findById(1)).thenReturn(Optional.of(existingErnaehrungsinformationen));

        // Konfigurieren des Mock-Objekts, um die aktualisierten Ernaehrungsinformationen zurückzugeben, wenn save aufgerufen wird
        when(ernaehrungsinformationenRepository.save(any(Ernaehrungsinformationen.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ruft die Methode updateErnaehrungsinformationen im ErnaehrungsinformationenService auf und speichert die aktualisierten Ernaehrungsinformationen
        Ernaehrungsinformationen updatedErnaehrungsinformationen = this.ernaehrungsinformationenService.updateErnaehrungsinformationen(1, newErnaehrungsinformationenDetails);

        // Überprüft, ob die aktualisierten Ernaehrungsinformationen die neuen Daten haben
        assertThat(updatedErnaehrungsinformationen.getKalorien()).isEqualTo(newErnaehrungsinformationenDetails.getKalorien());
        assertThat(updatedErnaehrungsinformationen.getProtein()).isEqualTo(newErnaehrungsinformationenDetails.getProtein());
        assertThat(updatedErnaehrungsinformationen.getKohlenhydrate()).isEqualTo(newErnaehrungsinformationenDetails.getKohlenhydrate());
        assertThat(updatedErnaehrungsinformationen.getFett()).isEqualTo(newErnaehrungsinformationenDetails.getFett());

        // Mithilfe von ArgumentCaptor die übergebene Entität beim Aufruf von ernaehrungsinformationenRepository.save(...) erfassen
        ArgumentCaptor<Ernaehrungsinformationen> ernaehrungsinformationenArgumentCaptor = ArgumentCaptor.forClass(Ernaehrungsinformationen.class);
        verify(ernaehrungsinformationenRepository).save(ernaehrungsinformationenArgumentCaptor.capture());
        Ernaehrungsinformationen captorValue = ernaehrungsinformationenArgumentCaptor.getValue();

        // Überprüft, ob die erfassten Ernaehrungsinformationen die neuen Daten haben
        assertThat(captorValue.getKalorien()).isEqualTo(newErnaehrungsinformationenDetails.getKalorien());
        assertThat(captorValue.getProtein()).isEqualTo(newErnaehrungsinformationenDetails.getProtein());
        assertThat(captorValue.getKohlenhydrate()).isEqualTo(newErnaehrungsinformationenDetails.getKohlenhydrate());
        assertThat(captorValue.getFett()).isEqualTo(newErnaehrungsinformationenDetails.getFett());
    }

    // Testet die Methode deleteErnaehrungsinformationen im ErnaehrungsinformationenService
    @Test
    void shouldDeleteErnaehrungsinformationen() {
        // Erstellt ein Beispiel-Ernaehrungsinformationen-Objekt
        Ernaehrungsinformationen existingErnaehrungsinformationen = new Ernaehrungsinformationen();
        existingErnaehrungsinformationen.setErnaehrungsinformationenID(1);
        existingErnaehrungsinformationen.setKalorien(2000);
        existingErnaehrungsinformationen.setProtein(50);
        existingErnaehrungsinformationen.setKohlenhydrate(250);
        existingErnaehrungsinformationen.setFett(70);

        // Konfigurieren des Mock-Objekts, um die existierenden Ernaehrungsinformationen zurückzugeben, wenn findById aufgerufen wird
        when(ernaehrungsinformationenRepository.findById(1)).thenReturn(Optional.of(existingErnaehrungsinformationen));

        // Ruft die Methode deleteErnaehrungsinformationen im ErnaehrungsinformationenService auf und speichert die gelöschten Ernaehrungsinformationen
        Ernaehrungsinformationen deletedErnaehrungsinformationen = this.ernaehrungsinformationenService.deleteErnaehrungsinformationen(1);

        // Überprüft, ob die gelöschten Ernaehrungsinformationen die erwarteten Daten haben
        assertThat(deletedErnaehrungsinformationen.getKalorien()).isEqualTo(existingErnaehrungsinformationen.getKalorien());
        assertThat(deletedErnaehrungsinformationen.getProtein()).isEqualTo(existingErnaehrungsinformationen.getProtein());
        assertThat(deletedErnaehrungsinformationen.getKohlenhydrate()).isEqualTo(existingErnaehrungsinformationen.getKohlenhydrate());
        assertThat(deletedErnaehrungsinformationen.getFett()).isEqualTo(existingErnaehrungsinformationen.getFett());

        // Überprüft, ob die Methode delete des ernaehrungsinformationenRepository aufgerufen wurde
        verify(ernaehrungsinformationenRepository).delete(existingErnaehrungsinformationen);
    }
}
