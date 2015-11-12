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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByJmbg", query = "SELECT s FROM Student s WHERE s.jmbg = :jmbg"),
    @NamedQuery(name = "Student.findByGodinaStudija", query = "SELECT s FROM Student s WHERE s.godinaStudija = :godinaStudija")})
public class Student implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Prijava> prijavaList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)

    @Column(name = "JMBG")
    private Integer jmbg;
    @Basic(optional = false)
    
    @Lob
    @Size(min = 0, max = 65535)
    @Column(name = "Ime")
    private String ime;
    @Basic(optional = false)
    
    @Lob
    @Size(min = 0, max = 65535)
    @Column(name = "Prezime")
    private String prezime;
    @Basic(optional = false)
    
    @Column(name = "GodinaStudija")
    private int godinaStudija;
    @Basic(optional = false)
    
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "BrojIndeksa")
    private String brojIndeksa;
    @Basic(optional = false)
    
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Ulica")
    private String ulica;
    @Basic(optional = false)
    
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Broj")
    private String broj;
    @Basic(optional = false)
  
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Sifra")
    private String sifra;
    @JoinColumn(name = "Smer", referencedColumnName = "SmerID")
    @ManyToOne(optional = false)
    private Smer smer;
    @JoinColumn(name = "Mesto", referencedColumnName = "Ptt")
    @ManyToOne(optional = false)
    private Mesto mesto;
    @JoinColumn(name = "Sluzbenik", referencedColumnName = "KorisnickoIme")
    @ManyToOne(optional = false)
    private Sluzbenik sluzbenik;

    public Student() {
    }

    public Student(Integer jmbg) {
        this.jmbg = jmbg;
    }

    public Student(Integer jmbg, String ime, String prezime, int godinaStudija, String brojIndeksa, String ulica, String broj, String sifra) {
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
        this.godinaStudija = godinaStudija;
        this.brojIndeksa = brojIndeksa;
        this.ulica = ulica;
        this.broj = broj;
        this.sifra = sifra;
    }

    public Integer getJmbg() {
        return jmbg;
    }

    public void setJmbg(Integer jmbg) {
        this.jmbg = jmbg;
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

    public int getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(int godinaStudija) {
        this.godinaStudija = godinaStudija;
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public Smer getSmer() {
        return smer;
    }

    public void setSmer(Smer smer) {
        this.smer = smer;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public Sluzbenik getSluzbenik() {
        return sluzbenik;
    }

    public void setSluzbenik(Sluzbenik sluzbenik) {
        this.sluzbenik = sluzbenik;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jmbg != null ? jmbg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.jmbg == null && other.jmbg != null) || (this.jmbg != null && !this.jmbg.equals(other.jmbg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Student[ jmbg=" + jmbg + " ]";
    }

    @XmlTransient
    public List<Prijava> getPrijavaList() {
        return prijavaList;
    }

    public void setPrijavaList(List<Prijava> prijavaList) {
        this.prijavaList = prijavaList;
    }
    
}
