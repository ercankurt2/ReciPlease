package de.htwberlin.reciplease.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Favoriten {
    @Id
    Integer favoritenID;

    // Many-to-One-Beziehung zur Entity-Klasse "Benutzer"
    @ManyToOne
    @JoinColumn(name = "benutzerID")
    private Benutzer benutzer;

    // Many-to-One-Beziehung zur Entity-Klasse "Rezept"
    @ManyToOne
    @JoinColumn(name = "rezeptID")
    private Rezept rezept;
}
