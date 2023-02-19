package com.esi.solution_tp1_2023.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="pr", types=Employe.class)
public interface EmployeProjection {
    public String getNom();

    @Value("#{target.departement.nom}")
    public String getNomDepartement();

    @Value("#{target.projet.duree}")
    public int getDuree();
}
