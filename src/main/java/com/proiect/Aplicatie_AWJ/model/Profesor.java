package com.proiect.Aplicatie_AWJ.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Set;

/**
 * Clasa pentru entitatea Profesor - reprezintă un cadru didactic
 * Relații: OneToMany cu Materie
 * Autentificare: Email (unic) și parolă criptată cu BCrypt
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Entity
@Table(name = "Profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDProfesor")
    private Integer idProfesor;

    @Column(name = "Nume", nullable = false, length = 50)
    private String nume;

    @Column(name = "Prenume", nullable = false, length = 50)
    private String prenume;

    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "Parola", nullable = false, length = 255)
    private String parola;

    @Column(name = "Departament", length = 100)
    private String departament;

    @Column(name = "Telefon", length = 20)
    private String telefon;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Materie> materii;

    public Profesor() {
    }

    public Profesor(String nume, String prenume, String email, String parola, String departament, String telefon) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.parola = parola;
        this.departament = departament;
        this.telefon = telefon;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Set<Materie> getMaterii() {
        return materii;
    }

    public void setMaterii(Set<Materie> materii) {
        this.materii = materii;
    }
}