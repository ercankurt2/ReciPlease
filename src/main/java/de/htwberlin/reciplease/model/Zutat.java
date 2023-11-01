package de.htwberlin.reciplease.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Zutat {
    @Id
    Integer zutatID;
    String name;
    Float menge;
    String einheit;
}
