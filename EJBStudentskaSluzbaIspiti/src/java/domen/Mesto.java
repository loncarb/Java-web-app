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
@Table(name = "mesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesto.findAll", query = "SELECT m FROM Mesto m"),
    @NamedQuery(name = "Mesto.findByPtt", query = "SELECT m FROM Mesto m WHERE m.ptt = :ptt")})
public class Mesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ptt")
    private Integer ptt;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Naziv")
    private String naziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mesto")
    private List<Student> studentList;

    public Mesto() {
    }

    public Mesto(Integer ptt) {
        this.ptt = ptt;
    }

    public Mesto(Integer ptt, String naziv) {
        this.ptt = ptt;
        this.naziv = naziv;
    }

    public Integer getPtt() {
        return ptt;
    }

    public void setPtt(Integer ptt) {
        this.ptt = ptt;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
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
        hash += (ptt != null ? ptt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesto)) {
            return false;
        }
        Mesto other = (Mesto) object;
        if ((this.ptt == null && other.ptt != null) || (this.ptt != null && !this.ptt.equals(other.ptt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Mesto[ ptt=" + ptt + " ]";
    }
    
}
