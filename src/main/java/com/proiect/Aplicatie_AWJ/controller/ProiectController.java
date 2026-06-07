package com.proiect.Aplicatie_AWJ.controller;

import com.proiect.Aplicatie_AWJ.model.Proiect;
import com.proiect.Aplicatie_AWJ.model.Student;
import com.proiect.Aplicatie_AWJ.model.Profesor;
import com.proiect.Aplicatie_AWJ.model.Materie;
import com.proiect.Aplicatie_AWJ.repository.ProiectRepository;
import com.proiect.Aplicatie_AWJ.repository.StudentRepository;
import com.proiect.Aplicatie_AWJ.repository.MaterieRepository;
import com.proiect.Aplicatie_AWJ.repository.ProfesorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pentru gestionarea proiectelor (CRUD complet)
 * Funcționalități: Creare, editare, ștergere, înscriere student la proiect
 * Securitate: Profesorii pot crea proiecte doar pentru materiile lor, păstrare studenți înscriși la editare
 * Validări: Status activitate, progres 0-100%, indicatori cheie
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Controller
@RequestMapping("/proiecte")
public class ProiectController {

    @Autowired
    private ProiectRepository proiectRepository;

    @Autowired
    private MaterieRepository materieRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping("/nou")
    public String proiectNou(Model model, Authentication auth) {
        model.addAttribute("proiect", new Proiect());

        String email = auth.getName();
        Profesor profesor = profesorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profesor not found"));

        List<Materie> materiiProfesor = materieRepository.findByProfesor(profesor);
        model.addAttribute("materii", materiiProfesor);

        return "form-proiect";
    }

    @GetMapping("/edit/{id}")
    public String editProiect(@PathVariable Integer id, Model model, Authentication auth) {
        Proiect proiect = proiectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID invalid: " + id));

        model.addAttribute("proiect", proiect);

        String email = auth.getName();
        Profesor profesor = profesorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profesor not found"));

        List<Materie> materiiProfesor = materieRepository.findByProfesor(profesor);
        model.addAttribute("materii", materiiProfesor);

        return "form-proiect";
    }

    @PostMapping("/salveaza")
    public String salveaza(@Valid @ModelAttribute("proiect") Proiect proiect,
                           BindingResult result,
                           Model model,
                           Authentication auth) {
        String email = auth.getName();
        Profesor profesor = profesorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profesor not found"));

        List<Materie> materiiProfesor = materieRepository.findByProfesor(profesor);

        if (result.hasErrors()) {
            model.addAttribute("materii", materiiProfesor);
            return "form-proiect";
        }

        if (proiect.getIdProiect() != null) {
            Proiect proiectExistent = proiectRepository.findById(proiect.getIdProiect())
                    .orElseThrow(() -> new RuntimeException("Proiect not found"));
            proiect.setStudenti(proiectExistent.getStudenti());
        }

        proiectRepository.save(proiect);
        return "redirect:/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        proiectRepository.deleteById(id);
        return "redirect:/dashboard";
    }

    @PostMapping("/inscriere/{id}")
    public String inscriereProiect(@PathVariable Integer id, Authentication auth) {
        Proiect proiect = proiectRepository.findById(id).orElseThrow();
        String email = auth.getName();
        Student student = studentRepository.findByEmail(email).orElseThrow();

        if (student.getProiecte().contains(proiect)) {
            return "redirect:/dashboard?alreadyEnrolled";
        }

        student.getProiecte().add(proiect);
        studentRepository.save(student);
        return "redirect:/dashboard?successInscriere";
    }
}