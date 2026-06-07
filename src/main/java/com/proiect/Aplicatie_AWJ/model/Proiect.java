package com.proiect.Aplicatie_AWJ.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clasa pentru entitatea Proiect - reprezintă o activitate academică cu status și progres
 * Relații: ManyToOne cu Materie, ManyToMany cu Student
 * Funcționalități: Gestionare status (În desfășurare, Finalizată, etc.) și progres (0-100%)
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Entity
@Table(name = "Proiect")
public class Proiect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProiect")
    private Integer idProiect;

    @NotBlank(message = "Titlul este obligatoriu")
    @Size(min = 5, max = 200, message = "Titlul trebuie să aibă între 5 și 200 de caractere.")
    @Column(name = "Titlu", nullable = false)
    private String titlu;

    @NotBlank(message = "Descrierea este obligatorie")
    @Size(min = 10, message = "Descrierea trebuie să fie mai detaliată (minim 10 caractere).")
    @Column(name = "Descriere", columnDefinition = "TEXT")
    private String descriere;

    @Column(name = "Cerinte", columnDefinition = "TEXT")
    private String cerinte;

    @Column(name = "Deadline", nullable = false, columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    @Column(name = "Status", length = 50)
    private String status = "În desfășurare";

    @Column(name = "Progres")
    @Min(value = 0, message = "Progresul nu poate fi negativ")
    @Max(value = 100, message = "Progresul nu poate depăși 100%")
    private Integer progres = 0;

    @Column(name = "IndicatoriCheie", columnDefinition = "TEXT")
    private String indicatoriCheie;

    @ManyToOne
    @JoinColumn(name = "IDMaterie")
    private Materie materie;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "StudentProiect",
            joinColumns = @JoinColumn(name = "IDProiect"),
            inverseJoinColumns = @JoinColumn(name = "IDStudent")
    )
    private List<Student> studenti = new ArrayList<>();

    public Proiect() {}

    public Integer getIdProiect() {
        return idProiect;
    }

    public void setIdProiect(Integer idProiect) {
        this.idProiect = idProiect;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getCerinte() {
        return cerinte;
    }

    public void setCerinte(String cerinte) {
        this.cerinte = cerinte;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getProgres() {
        return progres;
    }

    public void setProgres(Integer progres) {
        this.progres = progres;
    }

    public String getIndicatoriCheie() {
        return indicatoriCheie;
    }

    public void setIndicatoriCheie(String indicatoriCheie) {
        this.indicatoriCheie = indicatoriCheie;
    }

    public Materie getMaterie() {
        return materie;
    }

    public void setMaterie(Materie materie) {
        this.materie = materie;
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }
}