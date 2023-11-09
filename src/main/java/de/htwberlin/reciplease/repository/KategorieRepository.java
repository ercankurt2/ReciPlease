package de.htwberlin.reciplease.repository;

import de.htwberlin.reciplease.model.Kategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Generisches Interface mit Kategorie und ID (Integer)
// @Repository-Annotation kennzeichnet dieses Interface als Spring Data JPA-Repository
@Repository
public interface KategorieRepository extends JpaRepository<Kategorie, Integer> {

}
