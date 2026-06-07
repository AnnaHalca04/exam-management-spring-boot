package com.proiect.Aplicatie_AWJ.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configurare Spring Security pentru autentificare și autorizare
 * Funcționalități: Login personalizat, protecție rute, criptare parole cu BCrypt
 * Permisiuni: Rute publice (/login, /register) și rute protejate (/dashboard, /profil)
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/register", "/auth/**", "/css/**", "/js/**", "/api/proiecte/**", "/api/examene/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/dashboard", true).permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/").permitAll());
        return http.build();
    }
    @Bean public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}