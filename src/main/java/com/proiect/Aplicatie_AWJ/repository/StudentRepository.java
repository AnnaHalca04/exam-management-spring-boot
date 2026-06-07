package com.proiect.Aplicatie_AWJ.repository;

import com.proiect.Aplicatie_AWJ.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; // Importă clasa Optional

/**
 * Repository pentru entitatea Student
 * Metode: findByEmail - utilizat pentru autentificare
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByEmail(String email);
}