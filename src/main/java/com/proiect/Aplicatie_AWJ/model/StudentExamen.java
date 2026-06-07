package com.proiect.Aplicatie_AWJ.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Clasa pentru entitatea StudentExamen - legătură între Student și Examen
 * Stochează nota obținută la examen de către un student
 * Relații: ManyToOne cu Student, ManyToOne cu Examen
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Entity
@Table(name = "StudentExamen")
public class StudentExamen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_legatura")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IDStudent", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "IDExamen", nullable = false)
    private Examen examen;

    @Column(name = "Nota")
    private BigDecimal nota;

    public StudentExamen() {}

    public StudentExamen(Student student, Examen examen, BigDecimal nota) {
        this.student = student;
        this.examen = examen;
        this.nota = nota;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Examen getExamen() { return examen; }
    public void setExamen(Examen examen) { this.examen = examen; }

    public BigDecimal getNota() { return nota; }
    public void setNota(BigDecimal nota) { this.nota = nota; }
}