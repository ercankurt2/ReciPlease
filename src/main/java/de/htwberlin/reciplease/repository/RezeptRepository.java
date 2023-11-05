package de.htwberlin.reciplease.repository;

import de.htwberlin.reciplease.model.Rezept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Generisches Interface mit Rezept und ID (Integer)
// @Repository-Annotation kennzeichnet dieses Interface als Spring Data JPA-Repository
@Repository
public interface RezeptRepository extends JpaRepository<Rezept, Integer> {
    // Benutzerdefinierte Abfrage, um Rezepte nach Namen als String zu durchsuchen
    List<Rezept> findRezepteByNameContaining(String name);
}
