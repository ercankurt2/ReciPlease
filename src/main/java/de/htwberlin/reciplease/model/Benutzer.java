package de.htwberlin.reciplease.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Benutzer {
    @Id
    Integer benutzerID;
    String benutzername;
    String email;
    String passwort;

    // One-to-Many-Beziehung, wobei ein Benutzer mehrere Rezepte haben kann
    @OneToMany(mappedBy = "benutzer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rezept> rezepte = new ArrayList<>();

}
