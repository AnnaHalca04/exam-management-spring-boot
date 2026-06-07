package com.proiect.Aplicatie_AWJ.repository;

import com.proiect.Aplicatie_AWJ.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pentru entitatea Sala
 * Metode: findByCodSala - căutare rapidă după codul sălii
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {

    Sala findByCodSala(String codSala);
}