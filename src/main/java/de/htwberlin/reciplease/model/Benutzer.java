package de.htwberlin.reciplease.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Benutzer {
    @Id
    Integer benutzerID;
    String benutzername;
    String email;
    String passwort;
}
