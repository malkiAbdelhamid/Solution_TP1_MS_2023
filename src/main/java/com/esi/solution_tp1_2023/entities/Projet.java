package com.esi.solution_tp1_2023.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdProjet;

    @Column(nullable = false, length = 20)
    private String nom;

    private int duree;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "projet")
    private Collection<Employe> lesEmployes;
}
