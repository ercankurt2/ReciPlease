package de.htwberlin.reciplease.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Favoriten {
    @Id
    Integer favoritenID;
}
