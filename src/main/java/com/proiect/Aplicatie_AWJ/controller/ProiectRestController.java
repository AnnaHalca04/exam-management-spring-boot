/**
 * REST Controller pentru expunerea datelor despre proiecte prin API
 * Endpoint: GET /api/proiecte - returnează lista completă de proiecte în format JSON
 * Utilizare: Acces extern la date, testare cu POSTMAN
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

package com.proiect.Aplicatie_AWJ.controller;

import com.proiect.Aplicatie_AWJ.model.Proiect;
import com.proiect.Aplicatie_AWJ.repository.ProiectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/proiecte")
public class ProiectRestController {

    @Autowired
    private ProiectRepository proiectRepository;

    @GetMapping
    public List<Proiect> getAll() {
        return proiectRepository.findAll();
    }
}