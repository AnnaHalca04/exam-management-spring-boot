package com.proiect.Aplicatie_AWJ.repository;

import com.proiect.Aplicatie_AWJ.model.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository pentru entitatea Examen
 * Extinde JpaRepository pentru operațiuni CRUD automate
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Integer> {
    
    // Metodele CRUD sunt moștenite.
    // Metodă utilă pentru a găsi examenele unei materii specifice:
}