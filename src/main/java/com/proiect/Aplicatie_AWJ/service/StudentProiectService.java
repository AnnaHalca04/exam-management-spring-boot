package com.proiect.Aplicatie_AWJ.service;

import com.proiect.Aplicatie_AWJ.model.StudentProiect;
import com.proiect.Aplicatie_AWJ.model.StudentProiectID;
import java.math.BigDecimal;
import java.util.List;

/**
 * Interfață Service pentru gestionarea relației Student-Proiect
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

public interface StudentProiectService {
    List<StudentProiect> findAll();
    List<StudentProiect> getProiecteByStudentId(Integer studentId);
    StudentProiect save(StudentProiect studentProiect);
    void atribuieNota(StudentProiectID id, BigDecimal nota);
    void deleteById(StudentProiectID id);
}