package de.htwberlin.reciplease.service;

import de.htwberlin.reciplease.repository.KategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// @Service-Annotation kennzeichnet diese Klasse als einen Spring-Service,
// der die Geschäftslogik der Anwendung implementiert
@Service
@RequiredArgsConstructor // Lombok-Anmerkung, die einen Konstruktor erstellt
public class KategorieService {

    // Injektion des KategorieRepository, um auf Datenbankoperationen zuzugreifen
    private final KategorieRepository kategorieRepository;

}
