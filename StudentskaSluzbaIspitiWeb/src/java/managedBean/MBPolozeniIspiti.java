/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import domen.Predmet;
import domen.Prijava;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import sb.prijava.SBPrijavaLocal;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class MBPolozeniIspiti {

    List<Prijava> listaPolozenihIspta;
    @EJB
    SBPrijavaLocal sBPrijava;
    @ManagedProperty (value = "#{mBSesija}")
    MBSesija mBSesija;

    public MBSesija getmBSesija() {
        return mBSesija;
    }

    public void setmBSesija(MBSesija mBSesija) {
        this.mBSesija = mBSesija;
    }

    public List<Prijava> getListaPolozenihIspta() {
        return listaPolozenihIspta;
    }

    public void setListaPolozenihIspta(List<Prijava> listaPolozenihIspta) {
        this.listaPolozenihIspta = listaPolozenihIspta;
    }

    public SBPrijavaLocal getsBPrijava() {
        return sBPrijava;
    }

    public void setsBPrijava(SBPrijavaLocal sBPrijava) {
        this.sBPrijava = sBPrijava;
    }
    
    /**
     * Creates a new instance of MBPolozeniIspiti
     */
    public MBPolozeniIspiti() {
    }
    
    @PostConstruct
    public void postConstruct() {
        listaPolozenihIspta = sBPrijava.getPrijava(mBSesija.getUlogovaniStudent());
        
    }
}
