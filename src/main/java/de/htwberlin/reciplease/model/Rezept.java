package de.htwberlin.reciplease.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Rezept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer rezeptID;
    String titel;
    String beschreibung;
    String zubereitungszeit;
    String schwierigkeitsgrad;

    // Many-to-One-Beziehung zur Entity-Klasse "Benutzer"
    @ManyToOne
    @JoinColumn(name = "benutzerID")
    private Benutzer benutzer;

    // Many-to-One-Beziehung zur Entity-Klasse "Kategorie"
    @ManyToOne
    @JoinColumn(name = "kategorieID")
    private Kategorie kategorie;

    // One-to-One-Beziehung zur Entity-Klasse "Ernaehrungsinformationen"
    @OneToOne
    @JoinColumn(name = "erneahrungsinformationenID")
    private Ernaehrungsinformationen ernaehrungsinformationen;

    // Many-to-Many-Beziehung zur Entity-Klasse "Zutat"
    // Zum Hinzufügen von Zutaten-Listen zu Rezepten, Rezepte können also mehrere Zutaten umfassen
    @ManyToMany
    @JoinTable(
        name = "rezept_zutat", // Name der Join-Tabelle
        joinColumns = @JoinColumn(name = "rezeptID"),
        inverseJoinColumns = @JoinColumn(name = "zutatID")
    )
    private List<Zutat> zutaten;

}
