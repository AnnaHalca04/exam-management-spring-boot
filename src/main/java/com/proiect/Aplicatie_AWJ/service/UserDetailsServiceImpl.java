package com.proiect.Aplicatie_AWJ.service;

import com.proiect.Aplicatie_AWJ.model.Student;
import com.proiect.Aplicatie_AWJ.model.Profesor;
import com.proiect.Aplicatie_AWJ.repository.StudentRepository;
import com.proiect.Aplicatie_AWJ.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

/**
 * Implementare UserDetailsService pentru autentificare Spring Security
 * Funcționalități: Căutare utilizator după email în tabelele Student și Profesor
 * Securitate: Atribuire roluri (STUDENT/PROFESOR) pentru autorizare
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Student> studentOpt = studentRepository.findByEmail(email);
        if (studentOpt.isPresent()) {
            Student s = studentOpt.get();
            return new User(
                    s.getEmail(),
                    s.getParola(),
                    Collections.singletonList(new SimpleGrantedAuthority("STUDENT"))
            );
        }

        Optional<Profesor> profesorOpt = profesorRepository.findByEmail(email);
        if (profesorOpt.isPresent()) {
            Profesor p = profesorOpt.get();
            return new User(
                    p.getEmail(),
                    p.getParola(),
                    Collections.singletonList(new SimpleGrantedAuthority("PROFESOR"))
            );
        }

        throw new UsernameNotFoundException("Utilizatorul nu a fost găsit: " + email);
    }
}