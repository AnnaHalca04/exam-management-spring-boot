package com.proiect.Aplicatie_AWJ.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalTime;
import java.util.Date;

/**
 * Clasa pentru entitatea Examen - reprezintă un examen programat pentru o materie
 * Relații: ManyToOne cu Materie, ManyToOne cu Sala, OneToMany cu StudentExamen
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Entity
@Table(name = "Examen")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDExamen")
    private Integer idExamen;

    @Column(name = "Data", nullable = false, columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Data trebuie să fie în viitor")
    private Date data;

    @Column(name = "Ora", nullable = false)
    private LocalTime ora;

    @Column(name = "Durata", nullable = false)
    private Integer durata;

    @Column(name = "Sesiune", length = 50, nullable = false)
    @NotBlank(message = "Sesiunea este obligatorie")
    private String sesiune;

    @Column(name = "Tip", length = 20, nullable = false)
    @NotBlank(message = "Tipul este obligatoriu")
    private String tip;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IDSala", referencedColumnName = "IDSala")
    private Sala sala;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IDMaterie", referencedColumnName = "idMaterie")
    private Materie materie;

    public Examen() {}

    public Integer getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public Integer getDurata() {
        return durata;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }

    public String getSesiune() {
        return sesiune;
    }

    public void setSesiune(String sesiune) {
        this.sesiune = sesiune;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Materie getMaterie() {
        return materie;
    }

    public void setMaterie(Materie materie) {
        this.materie = materie;
    }
}