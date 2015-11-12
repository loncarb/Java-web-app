/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "sluzbenik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sluzbenik.findAll", query = "SELECT s FROM Sluzbenik s"),
    @NamedQuery(name = "Sluzbenik.findByKorisnickoIme", query = "SELECT s FROM Sluzbenik s WHERE s.korisnickoIme = :korisnickoIme")})
public class Sluzbenik implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sluzbenik")
    private List<Prijava> prijavaList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "KorisnickoIme")
    private String korisnickoIme;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "KorisnickaSifra")
    private String korisnickaSifra;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Prezime")
    private String prezime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sluzbenik")
    private List<Student> studentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sluzbenik")
    private List<Predmet> predmetList;

    public Sluzbenik() {
    }

    public Sluzbenik(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public Sluzbenik(String korisnickoIme, String korisnickaSifra, String ime, String prezime) {
        this.korisnickoIme = korisnickoIme;
        this.korisnickaSifra = korisnickaSifra;
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getKorisnickaSifra() {
        return korisnickaSifra;
    }

    public void setKorisnickaSifra(String korisnickaSifra) {
        this.korisnickaSifra = korisnickaSifra;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @XmlTransient
    public List<Predmet> getPredmetList() {
        return predmetList;
    }

    public void setPredmetList(List<Predmet> predmetList) {
        this.predmetList = predmetList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (korisnickoIme != null ? korisnickoIme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sluzbenik)) {
            return false;
        }
        Sluzbenik other = (Sluzbenik) object;
        if ((this.korisnickoIme == null && other.korisnickoIme != null) || (this.korisnickoIme != null && !this.korisnickoIme.equals(other.korisnickoIme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Sluzbenik[ korisnickoIme=" + korisnickoIme + " ]";
    }

    @XmlTransient
    public List<Prijava> getPrijavaList() {
        return prijavaList;
    }

    public void setPrijavaList(List<Prijava> prijavaList) {
        this.prijavaList = prijavaList;
    }
    
}
