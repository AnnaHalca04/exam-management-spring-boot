package com.proiect.Aplicatie_AWJ.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clasa pentru cheia primară compusă a tabelei StudentProiect
 * Implementează Serializable pentru compatibilitate JPA
 * Conține: studentId și proiectId
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Embeddable
public class StudentProiectID implements Serializable {

    private Integer studentId;
    private Integer proiectId;

    public StudentProiectID() {
    }

    public StudentProiectID(Integer studentId, Integer proiectId) {
        this.studentId = studentId;
        this.proiectId = proiectId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getProiectId() {
        return proiectId;
    }

    public void setProiectId(Integer proiectId) {
        this.proiectId = proiectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentProiectID that = (StudentProiectID) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(proiectId, that.proiectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, proiectId);
    }
}