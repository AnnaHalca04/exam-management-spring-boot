package com.proiect.Aplicatie_AWJ.model;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Clasa pentru entitatea Sala - reprezintă o sală de curs sau examen
 * Relații: OneToMany cu Examen
 * Atribute: Clădire, etaj, capacitate, cod sală (unic)
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Entity
@Table(name = "Sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSala;

    @Column(name = "Cladire", nullable = false, length = 50)
    private String cladire;

    @Column(name = "Etaj")
    private Integer etaj;

    @Column(name = "Capacitate", nullable = false)
    private Integer capacitate;

    @Column(name = "CodSala", nullable = false, unique = true, length = 20)
    private String codSala;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Examen> exameneGazduite; 

    public Sala() {
    }

    public Sala(String cladire, Integer etaj, Integer capacitate, String codSala) {
        this.cladire = cladire;
        this.etaj = etaj;
        this.capacitate = capacitate;
        this.codSala = codSala;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public String getCladire() {
        return cladire;
    }

    public void setCladire(String cladire) {
        this.cladire = cladire;
    }

    public Integer getEtaj() {
        return etaj;
    }

    public void setEtaj(Integer etaj) {
        this.etaj = etaj;
    }

    public Integer getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(Integer capacitate) {
        this.capacitate = capacitate;
    }

    public String getCodSala() {
        return codSala;
    }

    public void setCodSala(String codSala) {
        this.codSala = codSala;
    }

    public Set<Examen> getExameneGazduite() {
        return exameneGazduite;
    }

    public void setExameneGazduite(Set<Examen> exameneGazduite) {
        this.exameneGazduite = exameneGazduite;
    }
}