package com.proiect.Aplicatie_AWJ.repository;

import com.proiect.Aplicatie_AWJ.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository pentru entitatea Profesor
 * Metode: findByEmail - utilizat pentru autentificare
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
    Optional<Profesor> findByEmail(String email);
}