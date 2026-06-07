package com.proiect.Aplicatie_AWJ.service;

import com.proiect.Aplicatie_AWJ.model.Student;
import com.proiect.Aplicatie_AWJ.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementare Service pentru gestionarea studenților
 * Funcționalități: Înregistrare student cu criptare parolă BCrypt
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentServiceImpl(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Student inregistrareStudent(Student student) {
        student.setParola(passwordEncoder.encode(student.getParola()));
        return studentRepository.save(student);
    }
}