package com.proiect.Aplicatie_AWJ.controller;

import com.proiect.Aplicatie_AWJ.model.Student;
import com.proiect.Aplicatie_AWJ.repository.StudentRepository;
import com.proiect.Aplicatie_AWJ.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller pentru autentificare și înregistrare utilizatori
 * Gestionează: Login, înregistrare studenți cu criptare parolă BCrypt
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Controller
public class AuthController {

    @Autowired private StudentRepository studentRepository;
    @Autowired private ProfesorRepository profesorRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("student", new Student());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("student") Student student) {
        student.setParola(passwordEncoder.encode(student.getParola()));
        studentRepository.save(student);
        return "redirect:/login?success";
    }
}