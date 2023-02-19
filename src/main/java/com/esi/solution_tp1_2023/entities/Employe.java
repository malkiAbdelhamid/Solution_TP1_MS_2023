package com.esi.solution_tp1_2023.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor  @ToString
public class  Employe {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmp;

    @Column(nullable = false, length = 20)
    private String nom;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Temporal(TemporalType.DATE)
    private Date dateEmbauche;
    @Column(unique = true)
    private String email;

   @Nullable
   @Embedded
   private Diplome diplome;

    @ManyToOne
    @JoinColumn(name = "iddepart")
    private Departement departement;

    @ManyToOne
    @JoinColumn(name = "idprojet")
    private Projet projet;

}
