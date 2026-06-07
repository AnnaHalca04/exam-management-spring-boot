package com.proiect.Aplicatie_AWJ.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

/**
 * Clasa pentru entitatea Materie - reprezintă o disciplină predată la facultate
 * Relații: ManyToOne cu Profesor, OneToMany cu Proiect, OneToMany cu Examen
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Entity
@Table(name = "Materie")
public class Materie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMaterie;

    @Column(name = "Nume", nullable = false, length = 100)
    private String nume;

    @Column(name = "AnStudiu", nullable = false)
    private Integer anStudiu;

    @Column(name = "Semestru")
    private Integer semestru;

    @Column(name = "NumarCredite")
    private Integer numarCredite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDProfesor", nullable = false)
    @JsonIgnore
    private Profesor profesor;

    public Materie() {
    }

    public Materie(String nume, Integer anStudiu, Integer semestru, Integer numarCredite, Profesor profesor) {
        this.nume = nume;
        this.anStudiu = anStudiu;
        this.semestru = semestru;
        this.numarCredite = numarCredite;
        this.profesor = profesor;
    }

    public Integer getIdMaterie() {
        return idMaterie;
    }

    public void setIdMaterie(Integer idMaterie) {
        this.idMaterie = idMaterie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getAnStudiu() {
        return anStudiu;
    }

    public void setAnStudiu(Integer anStudiu) {
        this.anStudiu = anStudiu;
    }

    public Integer getSemestru() {
        return semestru;
    }

    public void setSemestru(Integer semestru) {
        this.semestru = semestru;
    }

    public Integer getNumarCredite() {
        return numarCredite;
    }

    public void setNumarCredite(Integer numarCredite) {
        this.numarCredite = numarCredite;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}