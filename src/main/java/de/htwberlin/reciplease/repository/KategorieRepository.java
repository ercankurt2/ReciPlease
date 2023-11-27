package de.htwberlin.reciplease.repository;

import de.htwberlin.reciplease.model.Kategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Generisches Interface mit Kategorie und ID (Integer)
// @Repository-Annotation kennzeichnet dieses Interface als Spring Data JPA-Repository
@Repository
public interface KategorieRepository extends JpaRepository<Kategorie, Integer> {
    // Benutzerdefinierte Abfrage, um Kategorien nach Kategoriennamen als String zu durchsuchen
    List<Kategorie> findKategorieByNameContaining(String name);

}
