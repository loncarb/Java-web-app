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
@Table(name = "profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesor p"),
    @NamedQuery(name = "Profesor.findByJmbg", query = "SELECT p FROM Profesor p WHERE p.jmbg = :jmbg")})
public class Profesor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "JMBG")
    private Integer jmbg;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profesor")
    private List<Predaje> predajeList;

    public Profesor() {
    }

    public Profesor(Integer jmbg) {
        this.jmbg = jmbg;
    }

    public Profesor(Integer jmbg, String ime, String prezime) {
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
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

    @XmlTransient
    public List<Predaje> getPredajeList() {
        return predajeList;
    }

    public void setPredajeList(List<Predaje> predajeList) {
        this.predajeList = predajeList;
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
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.jmbg == null && other.jmbg != null) || (this.jmbg != null && !this.jmbg.equals(other.jmbg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Profesor[ jmbg=" + jmbg + " ]";
    }
    
}
