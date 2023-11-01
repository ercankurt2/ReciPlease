package de.htwberlin.reciplease.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Ernaehrungsinformationen {
    @Id
    Integer ernaehrungsinformationenID;
    Integer kalorien;
    Integer protein;
    Integer kohlenhydrate;
    Integer fett;
}
