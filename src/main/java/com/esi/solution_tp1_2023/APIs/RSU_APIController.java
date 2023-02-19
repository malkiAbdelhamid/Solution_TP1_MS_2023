package com.esi.solution_tp1_2023.APIs;

import com.esi.solution_tp1_2023.entities.Diplome;
import com.esi.solution_tp1_2023.entities.Employe;
import com.esi.solution_tp1_2023.entities.Genre;
import com.esi.solution_tp1_2023.repositories.DepartementRepository;
import com.esi.solution_tp1_2023.repositories.EmployeRepository;
import com.esi.solution_tp1_2023.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rsu")
public class RSU_APIController {
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    EmployeRepository employeRepository;
    @Autowired
    ProjetRepository projetRepository;

    @GetMapping("departement/employe-projet")
//GET http://localhost:8081/rsu/departement/employe-projet?dn=production
    public List<Object[]> finEmployeByDepartName(@RequestParam("dn") String dn)
    {
        if(dn!=null)
        return employeRepository.getEmployes(dn);

        else return Collections.emptyList();
    }

    @PostMapping("employe")
    // POST localhost:8081/rsu/employe?iddepart=1
    public Employe addEmploye(@RequestBody Employe employe,
                              @RequestParam("iddepart") Long iddp )
    {
        employe.setDepartement(departementRepository.findById(iddp).get());

        return employeRepository.save(employe);
    }

    @PostMapping("employe2")
    // POST localhost:8081/rsu/employe2
    public Employe addEmploye2(@RequestBody Map<String, Object> payload)
    {
        Employe employe= new Employe();
        employe.setNom(payload.get("nom").toString());
        employe.setGenre(Genre.valueOf(payload.get("genre").toString()));
        employe.setEmail(payload.get("email").toString());

        Map<String,Object> Raw_diplome= (Map<String,Object>) payload.get("diplome");

        Diplome diplome =new Diplome(Raw_diplome.get("niveau").toString(),
                                     Raw_diplome.get("specialite").toString(),
                                   Integer.valueOf(Raw_diplome.get("anneeObtention").toString()).intValue());

        employe.setDiplome(diplome);


        Long idd=Long.valueOf(payload.get("iddepart").toString());
        employe.setDepartement(departementRepository.findById(idd).get());
        return employeRepository.save(employe);
    }

    @PutMapping("/employe/{id}")  //Put http://localhost:8081/rsu/employe/1
    public Employe updateClient(@PathVariable(value = "id") Long idEmp,
                                @RequestBody Map<String, Object>  payload) {

        if (employeRepository.findById(idEmp).isPresent()) {

            Employe employe=new Employe();
            employe.setIdEmp(idEmp);

            if(payload.get("nom")!=null) employe.setNom(payload.get("nom").toString());
            if(payload.get("genre")!=null) employe.setGenre(Genre.valueOf(payload.get("genre").toString()));
            if(payload.get("email")!=null) employe.setEmail(payload.get("email").toString());

            if(payload.get("iddepart")!=null) {
                Long iddepart= Long.valueOf(payload.get("iddepart").toString());
                employe.setDepartement(departementRepository.findById(iddepart).get());
            }
            return employeRepository.save(employe);
        }
        return null;
    }

    @PatchMapping("/employe/{id}")
    public Employe updateEtudiant2(@PathVariable(value="id") Long idEmp,
                                    @RequestBody Map<String, Object>  payload){

        if( employeRepository.findById(idEmp).isPresent()){

            Employe employe=employeRepository.findById(idEmp).get();

            if(payload.get("nom")!=null) employe.setNom(payload.get("nom").toString());
            if(payload.get("genre")!=null) employe.setGenre(Genre.valueOf(payload.get("genre").toString()));
            if(payload.get("email")!=null) employe.setEmail(payload.get("email").toString());

            if(payload.get("iddepart")!=null) {
                Long iddepart= Long.valueOf(payload.get("iddepart").toString());
                employe.setDepartement(departementRepository.findById(iddepart).get());
            }
            return employeRepository.save(employe);
        }
        return null;
    }


    @PutMapping("projet/{idprojet}/{idemp}")
    //PUT http://localhost:8081/rsu/projet/{idprojet}/{idemp}
    public String addEmployeToProjet(@PathVariable("idprojet") Long idProjet,
                                     @PathVariable("idemp") Long idEmp){
        if( employeRepository.findById(idEmp).isPresent() & projetRepository.findById(idProjet).isPresent()){
           Employe e=employeRepository.findById(idEmp).get();
           e.setProjet(projetRepository.findById(idProjet).get());
           employeRepository.save(e);

           return "l'employe a été correctement ajouté au projet";
        }

        return "l'employé ou projet n'existe pas";
    }

    @DeleteMapping("projet/{idprojet}/{idemp}")
    //Delete http://localhost:8081/rsu/projet/{idprojet}/{idemp}
    public String removeEmployeToProjet(@PathVariable("idprojet") Long idProjet,
                                     @PathVariable("idemp") Long idEmp){
        if( employeRepository.findById(idEmp).isPresent() & projetRepository.findById(idProjet).isPresent()){
            if(employeRepository.findById(idEmp).get().getProjet().getIdProjet()==idProjet){
                Employe e=employeRepository.findById(idEmp).get();
                e.setProjet(null);
                employeRepository.save(e);
                return "l'employe a été correctement supprimé du projet";
            }
           else {
                return "Cet employe n'est pas affecté à ce projet";
            }

        }

        return "l'employé ou projet n'existe pas";
    }
}
