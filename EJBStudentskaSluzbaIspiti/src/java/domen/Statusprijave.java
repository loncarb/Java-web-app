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
@Table(name = "statusprijave")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statusprijave.findAll", query = "SELECT s FROM Statusprijave s"),
    @NamedQuery(name = "Statusprijave.findByStatusID", query = "SELECT s FROM Statusprijave s WHERE s.statusID = :statusID")})
public class Statusprijave implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private List<Prijava> prijavaList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "StatusID")
    private Integer statusID;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Naziv")
    private String naziv;

    public Statusprijave() {
    }

    public Statusprijave(Integer statusID) {
        this.statusID = statusID;
    }

    public Statusprijave(Integer statusID, String naziv) {
        this.statusID = statusID;
        this.naziv = naziv;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
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
        hash += (statusID != null ? statusID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statusprijave)) {
            return false;
        }
        Statusprijave other = (Statusprijave) object;
        if ((this.statusID == null && other.statusID != null) || (this.statusID != null && !this.statusID.equals(other.statusID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Statusprijave[ statusID=" + statusID + " ]";
    }

    @XmlTransient
    public List<Prijava> getPrijavaList() {
        return prijavaList;
    }

    public void setPrijavaList(List<Prijava> prijavaList) {
        this.prijavaList = prijavaList;
    }
    
}
