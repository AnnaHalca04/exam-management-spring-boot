package com.proiect.Aplicatie_AWJ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller pentru pagina principală (index)
 * Gestionează ruta "/" - pagina de întâmpinare cu opțiuni de login
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        return "index"; // Deschide templates/index.html
    }
}