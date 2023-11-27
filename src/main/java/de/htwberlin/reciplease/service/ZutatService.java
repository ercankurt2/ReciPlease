package de.htwberlin.reciplease.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// @Service-Annotation kennzeichnet diese Klasse als einen Spring-Service,
// der die Geschäftslogik der Anwendung implementiert
@Service
@RequiredArgsConstructor // Lombok-Anmerkung, die einen Konstruktor erstellt
public class ZutatService {

    // Injektion des ZutatRepository, um auf Datenbankoperationen zuzugreifen
    private final ZutatService zutatService;

}
