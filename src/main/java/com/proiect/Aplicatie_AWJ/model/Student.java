package com.proiect.Aplicatie_AWJ.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clasa pentru entitatea Student - reprezintă un student înscris la facultate
 * Relații: ManyToMany cu Proiect
 * Filtrare: Studenții văd doar materiile și proiectele pentru anul lor de studiu
 * @author Halca Anna
 * @version 11 Ianuarie 2026
 */

@Entity
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDStudent")
    private Integer idStudent;

    @Column(name = "Nume", nullable = false)
    private String nume;

    @Column(name = "Prenume", nullable = false)
    private String prenume;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Parola", nullable = false)
    private String parola;

    @Column(name = "AnStudiu")
    private Integer anStudiu;

    @Column(name = "DataInregistrare")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataInregistrare;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name = "StudentProiect",
            joinColumns = @JoinColumn(name = "IDStudent"),
            inverseJoinColumns = @JoinColumn(name = "IDProiect")
    )
    private List<Proiect> proiecte = new ArrayList<>();

    public Student() {}


    public Integer getIdStudent() { return idStudent; }
    public void setIdStudent(Integer idStudent) { this.idStudent = idStudent; }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

    public String getPrenume() { return prenume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getParola() { return parola; }
    public void setParola(String parola) { this.parola = parola; }

    public Integer getAnStudiu() { return anStudiu; }
    public void setAnStudiu(Integer anStudiu) { this.anStudiu = anStudiu; }

    public Date getDataInregistrare() { return dataInregistrare; }
    public void setDataInregistrare(Date dataInregistrare) { this.dataInregistrare = dataInregistrare; }

    public List<Proiect> getProiecte() { return proiecte; }
    public void setProiecte(List<Proiect> proiecte) { this.proiecte = proiecte; }
}