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
@Table(name = "ispitnirok")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ispitnirok.findAll", query = "SELECT i FROM Ispitnirok i"),
    @NamedQuery(name = "Ispitnirok.findByIspitniRokID", query = "SELECT i FROM Ispitnirok i WHERE i.ispitniRokID = :ispitniRokID")})
public class Ispitnirok implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ispitniRok")
    private List<Prijava> prijavaList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IspitniRokID")
    private Integer ispitniRokID;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Naziv")
    private String naziv;

    public Ispitnirok() {
    }

    public Ispitnirok(Integer ispitniRokID) {
        this.ispitniRokID = ispitniRokID;
    }

    public Ispitnirok(Integer ispitniRokID, String naziv) {
        this.ispitniRokID = ispitniRokID;
        this.naziv = naziv;
    }

    public Integer getIspitniRokID() {
        return ispitniRokID;
    }

    public void setIspitniRokID(Integer ispitniRokID) {
        this.ispitniRokID = ispitniRokID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ispitniRokID != null ? ispitniRokID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ispitnirok)) {
            return false;
        }
        Ispitnirok other = (Ispitnirok) object;
        if ((this.ispitniRokID == null && other.ispitniRokID != null) || (this.ispitniRokID != null && !this.ispitniRokID.equals(other.ispitniRokID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Ispitnirok[ ispitniRokID=" + ispitniRokID + " ]";
    }

    @XmlTransient
    public List<Prijava> getPrijavaList() {
        return prijavaList;
    }

    public void setPrijavaList(List<Prijava> prijavaList) {
        this.prijavaList = prijavaList;
    }
    
}
