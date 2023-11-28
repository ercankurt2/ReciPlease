package de.htwberlin.reciplease.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Favoriten {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primärschlüssel wird automatisch generiert
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
