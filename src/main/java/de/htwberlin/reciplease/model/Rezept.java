package de.htwberlin.reciplease.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Rezept {
    @Id
    Integer rezeptID;
    String titel;
    String beschreibung;
    String zubereitungszeit;
    String schwierigkeitsgrad;
}
