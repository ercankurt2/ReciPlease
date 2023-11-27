package de.htwberlin.reciplease.repository;

import de.htwberlin.reciplease.model.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Generisches Interface mit Benutzer und ID (Integer)
// @Repository-Annotation kennzeichnet dieses Interface als Spring Data JPA-Repository
@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer, Integer> {
    // Benutzerdefinierte Abfrage, um Benutzer nach Benutzernamen als String zu durchsuchen
    List<Benutzer> findBenutzerByBenutzernameContaining(String benutzername);
}
