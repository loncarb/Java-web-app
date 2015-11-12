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
@Table(name = "predaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Predaje.findAll", query = "SELECT p FROM Predaje p"),
    @NamedQuery(name = "Predaje.findByPredajeID", query = "SELECT p FROM Predaje p WHERE p.predajeID = :predajeID"),
    @NamedQuery(name = "Predaje.findByGodinaStudija", query = "SELECT p FROM Predaje p WHERE p.godinaStudija = :godinaStudija")})
public class Predaje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PredajeID")
    private Integer predajeID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GodinaStudija")
    private int godinaStudija;
    @JoinColumn(name = "Smer", referencedColumnName = "SmerID")
    @ManyToOne(optional = false)
    private Smer smer;
    @JoinColumn(name = "Profesor", referencedColumnName = "JMBG")
    @ManyToOne(optional = false)
    private Profesor profesor;
    @JoinColumn(name = "Predmet", referencedColumnName = "PredmetID")
    @ManyToOne(optional = false)
    private Predmet predmet;

    public Predaje() {
    }

    public Predaje(Integer predajeID) {
        this.predajeID = predajeID;
    }

    public Predaje(Integer predajeID, int godinaStudija) {
        this.predajeID = predajeID;
        this.godinaStudija = godinaStudija;
    }

    public Integer getPredajeID() {
        return predajeID;
    }

    public void setPredajeID(Integer predajeID) {
        this.predajeID = predajeID;
    }

    public int getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(int godinaStudija) {
        this.godinaStudija = godinaStudija;
    }

    public Smer getSmer() {
        return smer;
    }

    public void setSmer(Smer smer) {
        this.smer = smer;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (predajeID != null ? predajeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Predaje)) {
            return false;
        }
        Predaje other = (Predaje) object;
        if ((this.predajeID == null && other.predajeID != null) || (this.predajeID != null && !this.predajeID.equals(other.predajeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Predaje[ predajeID=" + predajeID + " ]";
    }
    
}
