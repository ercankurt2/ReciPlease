package de.htwberlin.reciplease.repository;

import de.htwberlin.reciplease.model.Favoriten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Generisches Interface mit Favoriten und ID (Integer)
// @Repository-Annotation kennzeichnet dieses Interface als Spring Data JPA-Repository
@Repository
public interface FavoritenRepository extends JpaRepository<Favoriten, Integer> {

}
