package com.esi.solution_tp1_2023.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdDepart;

    @Column(nullable = false, length = 20)
    private String nom;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "departement")
    private Collection<Employe> lesEmployes;
}
