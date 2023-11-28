package de.htwberlin.reciplease.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Zutat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primärschlüssel wird automatisch generiert
    Integer zutatID;
    String name;
    Float menge;
    String einheit;
}
