package com.esi.solution_tp1_2023.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Embeddable
@Data @AllArgsConstructor @NoArgsConstructor
public class Diplome implements Serializable {

    private String niveau;
    private String specialite;

   /* @Column(nullable = true)
    private int anneeObtention ;*/
    private Integer anneeObtention ;
}
