package com.esi.solution_tp1_2023;

import com.esi.solution_tp1_2023.entities.*;
import com.esi.solution_tp1_2023.repositories.DepartementRepository;
import com.esi.solution_tp1_2023.repositories.EmployeRepository;
import com.esi.solution_tp1_2023.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@SpringBootApplication
public class SolutionTp12023Application implements CommandLineRunner {
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    EmployeRepository employeRepository;
    @Autowired
    ProjetRepository projetRepository;
    public static void main(String[] args) {
        SpringApplication.run(SolutionTp12023Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Departement d1= departementRepository.save(new Departement(null,"production", null));
        Departement d2= departementRepository.save(new Departement(null,"developpement", null));

        Projet p1=projetRepository.save(new Projet(null,"projet1",26,null));
        Projet p2=projetRepository.save(new Projet(null,"projet2",36,null));

        employeRepository.save(new Employe(null, "malki", Genre.Mr, Date.valueOf("2012-12-12") ,"a.amlki@esi-sba.dz",
                                          new Diplome("bac+3","informatique",2009), d1,p2));

        employeRepository.save(new Employe(null, "ali", Genre.Mr,  Date.valueOf("2014-10-12") ,"ali@esi-sba.dz",
                                           new Diplome("bac+5","informatique",2011), d1,p1));

        employeRepository.save(new Employe(null, "salima", Genre.Melle, Date.valueOf("2011-11-01") , "salima@yahoo.fr",
                                            new Diplome("bac+3","électronique",2010),d2,p2));

        employeRepository.save(new Employe(null, "karim", Genre.Mr,  Date.valueOf("2021-12-12") ,"karim@gmail.com",
                                            new Diplome("bac+1","informatique",2019),d2,p1));

        employeRepository.save(new Employe(null, "souad", Genre.Mme,  Date.valueOf("2022-12-12") ,"souad@esi-sba.dz",
                                             new Diplome("bac+5","mécanique",2019),d2,p1));


        for(Object[] row: employeRepository.getEmployes("production"))
            System.out.println(row[0]+" "+row[1]+" ");
    }
}
