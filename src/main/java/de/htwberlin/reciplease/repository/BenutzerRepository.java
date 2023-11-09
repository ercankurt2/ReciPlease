package de.htwberlin.reciplease.repository;

import de.htwberlin.reciplease.model.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Generisches Interface mit Benutzer und ID (Integer)
// @Repository-Annotation kennzeichnet dieses Interface als Spring Data JPA-Repository
@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer, Integer> {

}
