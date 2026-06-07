package com.proiect.Aplicatie_AWJ.repository;

import com.proiect.Aplicatie_AWJ.model.Proiect;
import com.proiect.Aplicatie_AWJ.model.Student;
import com.proiect.Aplicatie_AWJ.model.StudentProiect;
import com.proiect.Aplicatie_AWJ.model.StudentProiectID; // IMPORTUL CORECT
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository pentru entitatea StudentProiect
 * Metode: findByStudent_IdStudent, existsByStudentAndProiect
 * Utilizare: Verificare înscriere student la proiecte
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Repository
public interface StudentProiectRepository extends JpaRepository<StudentProiect, StudentProiectID> {

    List<StudentProiect> findByStudent_IdStudent(Integer idStudent);

    boolean existsByStudentAndProiect(Student student, Proiect proiect);
}