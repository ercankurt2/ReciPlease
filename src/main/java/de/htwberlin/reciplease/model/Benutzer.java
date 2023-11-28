package de.htwberlin.reciplease.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Benutzer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primärschlüssel wird automatisch generiert
    Integer benutzerID;
    String benutzername;
    String email;
    String passwort;

    // One-to-Many-Beziehung, wobei ein Benutzer mehrere Rezepte haben kann
    @OneToMany(mappedBy = "benutzer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Rezept> rezepte = new ArrayList<>();

}
