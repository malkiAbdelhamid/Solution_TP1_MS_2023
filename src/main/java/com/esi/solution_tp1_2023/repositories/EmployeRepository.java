package com.esi.solution_tp1_2023.repositories;



import com.esi.solution_tp1_2023.entities.Employe;
import com.esi.solution_tp1_2023.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe,Long> {

    List<Employe> findEmployeByEmailContaining(String domaine);

    List<Employe> findEmployesByEmailIsNotContaining(String d);

    @Query(" select count(e) from Employe e where e.genre= :g")
    int nbEmpployeByGenre(@Param("g") Genre g);

    @Query(value="select e.id_emp ,  p.nom  from employe e, departement d, projet p " +
            " where e.iddepart=d.id_depart and e.idprojet=p.id_projet and d.nom=:dn",nativeQuery = true)
   List<Object[]> getEmployes(@Param("dn") String dn);



}
