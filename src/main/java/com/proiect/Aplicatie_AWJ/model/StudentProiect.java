package com.proiect.Aplicatie_AWJ.model;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * Clasa pentru entitatea StudentProiect - legătură între Student și Proiect
 * Stochează data înscrierii, statusul și nota la proiect
 * Relații: ManyToOne cu Student, ManyToOne cu Proiect
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Entity
@Table(name = "StudentProiect")
public class StudentProiect {

    @EmbeddedId
    private StudentProiectID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name = "IDStudent", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("proiectId")
    @JoinColumn(name = "IDProiect", nullable = false)
    private Proiect proiect;

    @Column(name = "DataInscriere")
    private java.sql.Timestamp dataInscriere;

    @Column(name = "DataPredare")
    private Date dataPredare;

    @Column(name = "Status", length = 50)
    private String status;

    @Column(name = "Nota")
    private BigDecimal nota;

    public StudentProiect() {
        this.id = new StudentProiectID();
    }

    public StudentProiect(Student student, Proiect proiect, Timestamp dataInscriere, String status) {
        this.student = student;
        this.proiect = proiect;
        this.dataInscriere = dataInscriere;
        this.status = status;
        this.id = new StudentProiectID(student.getIdStudent(), proiect.getIdProiect());
    }

    public StudentProiectID getId() {
        return id;
    }

    public void setId(StudentProiectID id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Proiect getProiect() {
        return proiect;
    }

    public void setProiect(Proiect proiect) {
        this.proiect = proiect;
    }

    public Timestamp getDataInscriere() {
        return dataInscriere;
    }

    public void setDataInscriere(Timestamp dataInscriere) {
        this.dataInscriere = dataInscriere;
    }

    public Date getDataPredare() {
        return dataPredare;
    }

    public void setDataPredare(Date dataPredare) {
        this.dataPredare = dataPredare;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }
}