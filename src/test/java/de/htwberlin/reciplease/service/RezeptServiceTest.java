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

@ExtendWith(MockitoExtension.class)
class RezeptServiceTest {

    @Mock
    RezeptRepository rezeptRepository;

    @InjectMocks
    RezeptService rezeptService;

    @Test
    void shouldCreateRezept() {
        Rezept rezept = new Rezept();
        rezept.setTitel("Tomatensuppe");

        Rezept createdRezept = this.rezeptService.createRezept(rezept);

        assertThat(createdRezept.getTitel()).isEqualTo(rezept.getTitel());
        assertThat(createdRezept.getRezeptID()).isNotNull();

        ArgumentCaptor<Rezept> rezeptArgumentCaptor = ArgumentCaptor.forClass(Rezept.class);
        verify(rezeptRepository).save(rezeptArgumentCaptor.capture());
        Rezept captorValue = rezeptArgumentCaptor.getValue();

        assertThat(captorValue.getRezeptID()).isNotNull();
    }
}
