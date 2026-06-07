package com.proiect.Aplicatie_AWJ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clasa principală Spring Boot - punctul de intrare al aplicației
 * Configurare: Auto-configurare Spring Boot, pornire server Tomcat embedded
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@SpringBootApplication
public class AplicatieAwjApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplicatieAwjApplication.class, args);
	}

}

