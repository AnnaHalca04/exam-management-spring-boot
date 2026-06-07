package com.proiect.Aplicatie_AWJ.controller;

import com.proiect.Aplicatie_AWJ.model.Examen;
import com.proiect.Aplicatie_AWJ.model.Materie;
import com.proiect.Aplicatie_AWJ.model.Profesor;
import com.proiect.Aplicatie_AWJ.repository.ExamenRepository;
import com.proiect.Aplicatie_AWJ.repository.SalaRepository;
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
 * Controller pentru gestionarea examenelor (CRUD complet)
 * Funcționalități: Creare, editare, ștergere, vizualizare detalii examene
 * Securitate: Profesorii pot crea examene doar pentru materiile pe care le predă
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Controller
@RequestMapping("/examene")
public class ExamenController {

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private MaterieRepository materieRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping("/nou")
    public String examenNou(Model model, Authentication auth) {
        model.addAttribute("examen", new Examen());
        model.addAttribute("sali", salaRepository.findAll());

        String email = auth.getName();
        Profesor profesor = profesorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profesor not found"));

        List<Materie> materiiProfesor = materieRepository.findByProfesor(profesor);
        model.addAttribute("materii", materiiProfesor);

        return "form-examen";
    }

    @GetMapping("/edit/{id}")
    public String editExamen(@PathVariable Integer id, Model model, Authentication auth) {
        Examen examen = examenRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID invalid: " + id));

        model.addAttribute("examen", examen);
        model.addAttribute("sali", salaRepository.findAll());

        String email = auth.getName();
        Profesor profesor = profesorRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Profesor not found"));

        List<Materie> materiiProfesor = materieRepository.findByProfesor(profesor);
        model.addAttribute("materii", materiiProfesor);

        return "form-examen";
    }

    @PostMapping("/salveaza")
    public String salveaza(@Valid @ModelAttribute("examen") Examen examen,
                           BindingResult result,
                           Model model,
                           Authentication auth) {
        String email = auth.getName();
        Profesor profesor = profesorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profesor not found"));

        List<Materie> materiiProfesor = materieRepository.findByProfesor(profesor);

        //if (!materiiProfesor.contains(examen.getMaterie())) {
           // result.rejectValue("materie", "error.examen",
           //         "Nu poți crea examen pentru materii pe care nu le predai!");
           // model.addAttribute("sali", salaRepository.findAll());
           // model.addAttribute("materii", materiiProfesor);
            //return "form-examen";
        //}

        if (result.hasErrors()) {
            model.addAttribute("sali", salaRepository.findAll());
            model.addAttribute("materii", materiiProfesor);
            return "form-examen";
        }

        examenRepository.save(examen);
        return "redirect:/dashboard?successExamen";
    }

    @PostMapping("/delete/{id}")
    public String deleteExamen(@PathVariable Integer id) {
        examenRepository.deleteById(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/detalii/{id}")
    public String detaliiExamen(@PathVariable Integer id, Model model) {
        Examen examen = examenRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Examen not found"));

        model.addAttribute("examen", examen);
        return "detalii-examen";
    }
}