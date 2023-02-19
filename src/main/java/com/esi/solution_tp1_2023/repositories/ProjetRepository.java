package com.esi.solution_tp1_2023.repositories;

import com.esi.solution_tp1_2023.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository extends JpaRepository<Projet,Long> {
}
