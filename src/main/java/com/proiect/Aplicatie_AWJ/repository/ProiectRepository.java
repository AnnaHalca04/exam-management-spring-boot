package com.proiect.Aplicatie_AWJ.repository;

import com.proiect.Aplicatie_AWJ.model.Proiect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pentru entitatea Proiect
 * Extinde JpaRepository pentru operațiuni CRUD automate
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Repository
public interface ProiectRepository extends JpaRepository<Proiect, Integer> {
    
    // Metodă utilă pentru căutare/filtrare (cerințe de bonus)
}