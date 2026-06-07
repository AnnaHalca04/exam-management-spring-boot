package com.proiect.Aplicatie_AWJ.repository;

import com.proiect.Aplicatie_AWJ.model.Materie;
import com.proiect.Aplicatie_AWJ.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pentru entitatea Materie cu metode de căutare personalizate
 * Metode: findByAnStudiu, findByProfesor, findNumeById
 * Utilizare: Filtrare materii după an de studiu sau profesor
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Repository
public interface MaterieRepository extends JpaRepository<Materie, Integer> {

    @Query("SELECT m.nume FROM Materie m WHERE m.idMaterie = :id")
    String findNumeById(@Param("id") Integer id);

    List<Materie> findByAnStudiu(Integer anStudiu);

    List<Materie> findByProfesor(Profesor profesor);
}