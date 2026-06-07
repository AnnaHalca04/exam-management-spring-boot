package com.proiect.Aplicatie_AWJ.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proiect.Aplicatie_AWJ.model.StudentProiect;
import com.proiect.Aplicatie_AWJ.model.StudentProiectID;
import com.proiect.Aplicatie_AWJ.repository.StudentProiectRepository;

/**
 * Implementare Service pentru gestionarea relației Student-Proiect
 * Funcționalități: Gestionare înscrieri, atribuire note, ștergere legături
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Service
public class StudentProiectServiceImpl implements StudentProiectService {

    @Autowired
    private StudentProiectRepository studentProiectRepository;

    @Override
    public List<StudentProiect> findAll() {
        return studentProiectRepository.findAll();
    }

    @Override
    public List<StudentProiect> getProiecteByStudentId(Integer studentId) {
        return studentProiectRepository.findByStudent_IdStudent(studentId);
    }

    @Override
    public StudentProiect save(StudentProiect studentProiect) {
        return studentProiectRepository.save(studentProiect);
    }

    @Override
    public void atribuieNota(StudentProiectID id, BigDecimal nota) {
        StudentProiect sp = studentProiectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proiectul nu a fost găsit"));
        sp.setNota(nota);
        studentProiectRepository.save(sp);
    }

    @Override
    public void deleteById(StudentProiectID id) {
        studentProiectRepository.deleteById(id);
    }
}