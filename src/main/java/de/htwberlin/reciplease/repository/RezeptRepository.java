package de.htwberlin.reciplease.repository;

import de.htwberlin.reciplease.model.Rezept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Generisches Interface erstellen mit Rezept und ID (Integer)
@Repository
public interface RezeptRepository extends JpaRepository<Rezept, Integer> {

}
