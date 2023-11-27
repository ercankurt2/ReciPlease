package de.htwberlin.reciplease.repository;

import de.htwberlin.reciplease.model.Zutat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Generisches Interface mit Zutat und ID (Integer)
// @Repository-Annotation kennzeichnet dieses Interface als Spring Data JPA-Repository
@Repository
public interface ZutatRepository extends JpaRepository<Zutat, Integer> {
    // Benutzerdefinierte Abfrage, um Zutaten nach Zutatnamen als String zu durchsuchen
    List<Zutat> findZutatByZutatnameContaining(String zutatname);
}
