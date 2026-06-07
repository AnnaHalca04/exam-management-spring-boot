package com.proiect.Aplicatie_AWJ.controller;

import com.proiect.Aplicatie_AWJ.model.*;
import com.proiect.Aplicatie_AWJ.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller pentru dashboard-ul principal și profilul utilizatorului
 * Funcționalități: Afișare proiecte/examene filtrate, statistici studenți, sortare, actualizare profil
 * Securitate: Filtrare după rolul utilizatorului (Student/Profesor) și anul de studiu/materie predată
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Controller
public class DashboardController {

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private ProiectRepository proiectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private MaterieRepository materieRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model,
                            Authentication auth,
                            @RequestParam(required = false) String sort,
                            @RequestParam(required = false) String filter) {

        Optional<Student> studentOpt = studentRepository.findByEmail(auth.getName());
        boolean isStudent = studentOpt.isPresent();
        model.addAttribute("isStudent", isStudent);

        if (isStudent) {
            Student student = studentOpt.get();
            Integer anStudiu = student.getAnStudiu();

            List<Materie> materiiStudent = materieRepository.findByAnStudiu(anStudiu);

            List<Examen> exameneStudent = examenRepository.findAll().stream()
                    .filter(e -> materiiStudent.contains(e.getMaterie()))
                    .sorted(Comparator.comparing(Examen::getData))
                    .collect(Collectors.toList());

            List<Proiect> proiecteStudent = proiectRepository.findAll().stream()
                    .filter(p -> materiiStudent.contains(p.getMaterie()))
                    .collect(Collectors.toList());

            if ("deadline".equals(sort)) {
                proiecteStudent = proiecteStudent.stream()
                        .sorted(Comparator.comparing(Proiect::getDeadline))
                        .collect(Collectors.toList());
            } else if ("status".equals(sort)) {
                proiecteStudent = proiecteStudent.stream()
                        .sorted(Comparator.comparing(Proiect::getStatus))
                        .collect(Collectors.toList());
            } else if ("progres".equals(sort)) {
                proiecteStudent = proiecteStudent.stream()
                        .sorted(Comparator.comparing(Proiect::getProgres).reversed())
                        .collect(Collectors.toList());
            }

            if (filter != null && !filter.isEmpty()) {
                proiecteStudent = proiecteStudent.stream()
                        .filter(p -> p.getStatus().equalsIgnoreCase(filter))
                        .collect(Collectors.toList());
            }

            model.addAttribute("examene", exameneStudent);
            model.addAttribute("proiecte", proiecteStudent);

            LocalDate acum = LocalDate.now();
            List<Proiect> proiecteStudentului = student.getProiecte();

            List<Proiect> proiecteUrgente = proiecteStudentului.stream()
                    .filter(p -> {
                        LocalDate deadline = p.getDeadline().toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate();
                        long zileRamase = ChronoUnit.DAYS.between(acum, deadline);
                        return zileRamase >= 0 && zileRamase < 7;
                    })
                    .collect(Collectors.toList());

            model.addAttribute("proiecteUrgente", proiecteUrgente);

            List<Integer> proiecteInscrise = proiecteStudentului.stream()
                    .map(Proiect::getIdProiect)
                    .collect(Collectors.toList());
            model.addAttribute("proiecteInscrise", proiecteInscrise);

            if (!proiecteStudentului.isEmpty()) {
                double progresTotal = proiecteStudentului.stream()
                        .mapToInt(p -> p.getProgres() != null ? p.getProgres() : 0)
                        .average()
                        .orElse(0.0);
                model.addAttribute("progresTotal", Math.round(progresTotal));
            } else {
                model.addAttribute("progresTotal", 0);
            }

        } else {
            Optional<Profesor> profesorOpt = profesorRepository.findByEmail(auth.getName());

            if (profesorOpt.isPresent()) {
                Profesor profesor = profesorOpt.get();
                List<Materie> materiiProfesor = materieRepository.findByProfesor(profesor);

                List<Examen> exameneProfesor = examenRepository.findAll().stream()
                        .filter(e -> materiiProfesor.contains(e.getMaterie()))
                        .sorted(Comparator.comparing(Examen::getData))
                        .collect(Collectors.toList());

                List<Proiect> proiecteProfesor = proiectRepository.findAll().stream()
                        .filter(p -> materiiProfesor.contains(p.getMaterie()))
                        .collect(Collectors.toList());

                if ("deadline".equals(sort)) {
                    proiecteProfesor = proiecteProfesor.stream()
                            .sorted(Comparator.comparing(Proiect::getDeadline))
                            .collect(Collectors.toList());
                } else if ("status".equals(sort)) {
                    proiecteProfesor = proiecteProfesor.stream()
                            .sorted(Comparator.comparing(Proiect::getStatus))
                            .collect(Collectors.toList());
                }

                model.addAttribute("examene", exameneProfesor);
                model.addAttribute("proiecte", proiecteProfesor);
            }

            model.addAttribute("proiecteUrgente", List.of());
            model.addAttribute("proiecteInscrise", List.of());
        }

        return "dashboard";
    }

    @GetMapping("/profil")
    public String profil(Model model, Authentication auth) {
        String email = auth.getName();

        Optional<Student> studentOpt = studentRepository.findByEmail(email);
        if (studentOpt.isPresent()) {
            model.addAttribute("user", studentOpt.get());
            model.addAttribute("isStudent", true);
        } else {
            Optional<Profesor> profesorOpt = profesorRepository.findByEmail(email);
            if (profesorOpt.isPresent()) {
                model.addAttribute("user", profesorOpt.get());
                model.addAttribute("isStudent", false);
            }
        }

        return "profil";
    }

    @PostMapping("/profil/update/student")
    public String updateStudent(@RequestParam Integer anStudiu,
                                @RequestParam String nume,
                                @RequestParam String prenume,
                                Authentication auth) {
        String email = auth.getName();
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setAnStudiu(anStudiu);
        student.setNume(nume);
        student.setPrenume(prenume);
        studentRepository.save(student);

        return "redirect:/profil?success";
    }

    @PostMapping("/profil/update/profesor")
    public String updateProfesor(@RequestParam String departament,
                                 @RequestParam String telefon,
                                 @RequestParam String nume,
                                 @RequestParam String prenume,
                                 Authentication auth) {
        String email = auth.getName();
        Profesor profesor = profesorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profesor not found"));

        profesor.setDepartament(departament);
        profesor.setTelefon(telefon);
        profesor.setNume(nume);
        profesor.setPrenume(prenume);
        profesorRepository.save(profesor);

        return "redirect:/profil?success";
    }
}