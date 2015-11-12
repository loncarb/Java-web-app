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
@Table(name = "smer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Smer.findAll", query = "SELECT s FROM Smer s"),
    @NamedQuery(name = "Smer.findBySmerID", query = "SELECT s FROM Smer s WHERE s.smerID = :smerID")})
public class Smer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SmerID")
    private Integer smerID;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Naziv")
    private String naziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "smer")
    private List<Predaje> predajeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "smer")
    private List<Student> studentList;

    public Smer() {
    }

    public Smer(Integer smerID) {
        this.smerID = smerID;
    }

    public Smer(Integer smerID, String naziv) {
        this.smerID = smerID;
        this.naziv = naziv;
    }

    public Integer getSmerID() {
        return smerID;
    }

    public void setSmerID(Integer smerID) {
        this.smerID = smerID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<Predaje> getPredajeList() {
        return predajeList;
    }

    public void setPredajeList(List<Predaje> predajeList) {
        this.predajeList = predajeList;
    }

    @XmlTransient
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (smerID != null ? smerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Smer)) {
            return false;
        }
        Smer other = (Smer) object;
        if ((this.smerID == null && other.smerID != null) || (this.smerID != null && !this.smerID.equals(other.smerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Smer[ smerID=" + smerID + " ]";
    }
    
}
