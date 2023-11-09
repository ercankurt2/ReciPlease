package de.htwberlin.reciplease.repository;

import de.htwberlin.reciplease.model.Ernaehrungsinformationen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Generisches Interface mit Ernaehrungsinformationen und ID (Integer)
// @Repository-Annotation kennzeichnet dieses Interface als Spring Data JPA-Repository
@Repository
public interface ErnaehrungsinformationenRepository extends JpaRepository<Ernaehrungsinformationen, Integer> {

}
