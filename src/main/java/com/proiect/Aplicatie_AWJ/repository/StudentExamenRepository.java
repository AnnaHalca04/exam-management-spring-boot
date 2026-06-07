package com.proiect.Aplicatie_AWJ.repository;

import com.proiect.Aplicatie_AWJ.model.StudentExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository pentru entitatea StudentExamen
 * Metode personalizate: deleteByStudentAndExamenId pentru ștergere participare
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Repository
public interface StudentExamenRepository extends JpaRepository<StudentExamen, Integer> {


    @Transactional
    @Modifying
    @Query("DELETE FROM StudentExamen se WHERE se.student.idStudent = ?1 AND se.examen.idExamen = ?2")
    void deleteByStudentAndExamenId(Integer idStudent, Integer idExamen);
}