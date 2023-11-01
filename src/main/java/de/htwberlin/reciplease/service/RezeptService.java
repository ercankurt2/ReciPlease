package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.model.Rezept;
import de.htwberlin.reciplease.repository.RezeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RezeptService {

    private RezeptRepository rezeptRepository;

    public Rezept createRezept(Rezept rezept) {
        rezeptRepository.save(rezept);

        return rezept;
    }
}
