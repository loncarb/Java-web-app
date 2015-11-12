/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import domen.Predmet;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import sb.predmet.SBPredmetLocal;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class MBIspit {

    boolean greska;
    boolean uspeh;
    private Predmet predmet;
    @EJB
    SBPredmetLocal sBPredmet;
    @ManagedProperty(value = "#{mBSesija}")
    MBSesija sesija;

    public boolean isGreska() {
        return greska;
    }

    public void setGreska(boolean greska) {
        this.greska = greska;
    }

    public boolean isUspeh() {
        return uspeh;
    }

    public void setUspeh(boolean uspeh) {
        this.uspeh = uspeh;
    }

    public MBSesija getSesija() {
        return sesija;
    }

    public void setSesija(MBSesija sesija) {
        this.sesija = sesija;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public SBPredmetLocal getsBPredmet() {
        return sBPredmet;
    }

    public void setsBPredmet(SBPredmetLocal sBPredmet) {
        this.sBPredmet = sBPredmet;
    }

    /**
     * Creates a new instance of MBIspit
     */
    public MBIspit() {
    }

    @PostConstruct
    public void postConstruct() {
        predmet = new Predmet();
        int predmetID = sBPredmet.getPredmetID();
        predmet.setPredmetID(predmetID);
        greska = false;
        uspeh = false;
    }

    public void savePredmet() throws Exception {
        predmet.setSluzbenik(sesija.getUlogovaniSluzbenik());
        if (predmet.getNaziv() == null) {
            greska = true;
            uspeh = false;
            FacesContext.getCurrentInstance().addMessage("greska", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate popuniti sva polja za unos ispita", ""));
        } else {
            if (sBPredmet.savePredmet(predmet)) {
                FacesContext.getCurrentInstance().addMessage("uspeh", new FacesMessage("Student je sacuvan"));
                greska = false;
                uspeh = true;
            } else {
                greska = true;
                uspeh = false;
                FacesContext.getCurrentInstance().addMessage("greska", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Doslo je do greske i student nije sacuvan", ""));
            }
        }
    }
}
