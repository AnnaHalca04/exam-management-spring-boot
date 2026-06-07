package com.proiect.Aplicatie_AWJ.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clasa pentru cheia primară compusă a tabelei StudentExamen
 * Implementează Serializable pentru compatibilitate JPA
 * Conține: studentId și examenId
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Embeddable
public class StudentExamenID implements Serializable {

    private Integer studentId;
    private Integer examenId;

    public StudentExamenID() { }

    public StudentExamenID(Integer studentId, Integer examenId) {
        this.studentId = studentId;
        this.examenId = examenId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getExamenId() {
        return examenId;
    }

    public void setExamenId(Integer examenId) {
        this.examenId = examenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentExamenID that = (StudentExamenID) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(examenId, that.examenId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, examenId);
    }
}