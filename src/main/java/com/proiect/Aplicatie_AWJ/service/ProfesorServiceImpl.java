package com.proiect.Aplicatie_AWJ.service;

import com.proiect.Aplicatie_AWJ.model.Profesor;
import com.proiect.Aplicatie_AWJ.repository.ProfesorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementare Service pentru gestionarea profesorilor
 * Funcționalități: Înregistrare profesor cu criptare parolă BCrypt
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfesorServiceImpl(ProfesorRepository profesorRepository, PasswordEncoder passwordEncoder) {
        this.profesorRepository = profesorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Profesor inregistrareProfesor(Profesor profesor) {
        profesor.setParola(passwordEncoder.encode(profesor.getParola()));
        return profesorRepository.save(profesor);
    }
}