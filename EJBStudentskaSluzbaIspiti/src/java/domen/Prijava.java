/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "prijava")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prijava.findAll", query = "SELECT p FROM Prijava p"),
    @NamedQuery(name = "Prijava.findByPrijavaID", query = "SELECT p FROM Prijava p WHERE p.prijavaID = :prijavaID"),
    @NamedQuery(name = "Prijava.findByOcena", query = "SELECT p FROM Prijava p WHERE p.ocena = :ocena")})
public class Prijava implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PrijavaID")
    private Integer prijavaID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ocena")
    private int ocena;
    @JoinColumn(name = "Sluzbenik", referencedColumnName = "KorisnickoIme")
    @ManyToOne(optional = false)
    private Sluzbenik sluzbenik;
    @JoinColumn(name = "Student", referencedColumnName = "JMBG")
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "Predmet", referencedColumnName = "PredmetID")
    @ManyToOne(optional = false)
    private Predmet predmet;
    @JoinColumn(name = "IspitniRok", referencedColumnName = "IspitniRokID")
    @ManyToOne(optional = false)
    private Ispitnirok ispitniRok;
    @JoinColumn(name = "Status", referencedColumnName = "StatusID")
    @ManyToOne(optional = false)
    private Statusprijave status;

    public Prijava() {
    }

    public Prijava(Integer prijavaID) {
        this.prijavaID = prijavaID;
    }

    public Prijava(Integer prijavaID, int ocena) {
        this.prijavaID = prijavaID;
        this.ocena = ocena;
    }

    public Integer getPrijavaID() {
        return prijavaID;
    }

    public void setPrijavaID(Integer prijavaID) {
        this.prijavaID = prijavaID;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public Sluzbenik getSluzbenik() {
        return sluzbenik;
    }

    public void setSluzbenik(Sluzbenik sluzbenik) {
        this.sluzbenik = sluzbenik;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Ispitnirok getIspitniRok() {
        return ispitniRok;
    }

    public void setIspitniRok(Ispitnirok ispitniRok) {
        this.ispitniRok = ispitniRok;
    }

    public Statusprijave getStatus() {
        return status;
    }

    public void setStatus(Statusprijave status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prijavaID != null ? prijavaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prijava)) {
            return false;
        }
        Prijava other = (Prijava) object;
        if ((this.prijavaID == null && other.prijavaID != null) || (this.prijavaID != null && !this.prijavaID.equals(other.prijavaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Prijava[ prijavaID=" + prijavaID + " ]";
    }
    
}
