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
@Table(name = "predmet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Predmet.findAll", query = "SELECT p FROM Predmet p"),
    @NamedQuery(name = "Predmet.findByPredmetID", query = "SELECT p FROM Predmet p WHERE p.predmetID = :predmetID"),
    @NamedQuery(name = "Predmet.findByEspb", query = "SELECT p FROM Predmet p WHERE p.espb = :espb")})
public class Predmet implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "predmet")
    private List<Prijava> prijavaList;
    @Column(name = "promenljiva")
    private Boolean promenljiva;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PredmetID")
    private Integer predmetID;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESPB")
    private int espb;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "predmet")
    private List<Predaje> predajeList;
    @JoinColumn(name = "Sluzbenik", referencedColumnName = "KorisnickoIme")
    @ManyToOne(optional = false)
    private Sluzbenik sluzbenik;

    public Predmet() {
    }

    public Predmet(Integer predmetID) {
        this.predmetID = predmetID;
    }

    public Predmet(Integer predmetID, String naziv, int espb) {
        this.predmetID = predmetID;
        this.naziv = naziv;
        this.espb = espb;
    }

    public Integer getPredmetID() {
        return predmetID;
    }

    public void setPredmetID(Integer predmetID) {
        this.predmetID = predmetID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    @XmlTransient
    public List<Predaje> getPredajeList() {
        return predajeList;
    }

    public void setPredajeList(List<Predaje> predajeList) {
        this.predajeList = predajeList;
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
        hash += (predmetID != null ? predmetID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Predmet)) {
            return false;
        }
        Predmet other = (Predmet) object;
        if ((this.predmetID == null && other.predmetID != null) || (this.predmetID != null && !this.predmetID.equals(other.predmetID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Predmet[ predmetID=" + predmetID + " ]";
    }

    public Boolean getPromenljiva() {
        return promenljiva;
    }

    public void setPromenljiva(Boolean promenljiva) {
        this.promenljiva = promenljiva;
    }

    @XmlTransient
    public List<Prijava> getPrijavaList() {
        return prijavaList;
    }

    public void setPrijavaList(List<Prijava> prijavaList) {
        this.prijavaList = prijavaList;
    }
    
}
