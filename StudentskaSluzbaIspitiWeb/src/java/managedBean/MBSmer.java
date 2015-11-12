/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import domen.Predaje;
import domen.Predmet;
import domen.Smer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sb.predmet.SBPredmetLocal;
import sb.smer.SBSmerLocal;

/**
 *
 * @author user
 */
@ManagedBean
@ViewScoped
public class MBSmer {

    boolean greska;
    boolean uspeh;
    Predmet predmet;
    int brojac;
    List<Predmet> listaPredmeta;
    private Smer smer;
    private List<Predmet> copyListaPredmeta;
    List<Predaje> listaIzabranihPredmeta;
    @EJB
    SBPredmetLocal sBPredmet;
    @EJB
    SBSmerLocal sBSmer;
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

    public List<Predmet> getCopyListaPredmeta() {
        return copyListaPredmeta;
    }

    public void setCopyListaPredmeta(List<Predmet> copyListaPredmeta) {
        this.copyListaPredmeta = copyListaPredmeta;
    }

    public int getBrojac() {
        return brojac;
    }

    public void setBrojac(int brojac) {
        this.brojac = brojac;
    }

    public List<Predaje> getListaIzabranihPredmeta() {
        return listaIzabranihPredmeta;
    }

    public void setListaIzabranihPredmeta(List<Predaje> listaIzabranihPredmeta) {
        this.listaIzabranihPredmeta = listaIzabranihPredmeta;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public List<Predmet> getListaPredmeta() {
        return listaPredmeta;
    }

    public void setListaPredmeta(List<Predmet> listaPredmeta) {
        this.listaPredmeta = listaPredmeta;
    }

    public SBPredmetLocal getsBPredmet() {
        return sBPredmet;
    }

    public void setsBPredmet(SBPredmetLocal sBPredmet) {
        this.sBPredmet = sBPredmet;
    }

    public MBSesija getSesija() {
        return sesija;
    }

    public void setSesija(MBSesija sesija) {
        this.sesija = sesija;
    }

    public Smer getSmer() {
        return smer;
    }

    public void setSmer(Smer smer) {
        this.smer = smer;
    }

    public SBSmerLocal getsBSmer() {
        return sBSmer;
    }

    public void setsBSmer(SBSmerLocal sBSmer) {
        this.sBSmer = sBSmer;
    }

    /**
     * Creates a new instance of MBSmer
     */
    public MBSmer() {
    }

    @PostConstruct
    public void postConstruct() {
        listaIzabranihPredmeta = new ArrayList<>();
        brojac = 1;
        copyListaPredmeta = new ArrayList<>();
        smer = new Smer();
        int smerID = sBSmer.getSmerID();
        smer.setSmerID(smerID);
        listaPredmeta = sBPredmet.getPredmet();
        greska = false;
        uspeh = false;
        for (Predmet p : listaPredmeta) {
            copyListaPredmeta.add(p);
        }
//        copyListaPredmeta.addAll(listaPredmeta);
    }

    public void choosePredmet() {
        Iterator<Predmet> i = listaPredmeta.iterator();
        while (i.hasNext()) {
            System.out.println("ovde");
            Predmet p = i.next();
            if (p.getPromenljiva()) {
                Predaje predaje = new Predaje();
                predaje.setGodinaStudija(brojac);
                predaje.setPredmet(p);
                predaje.setSmer(smer);
                listaIzabranihPredmeta.add(predaje);
                copyListaPredmeta.remove(p);
            }
        }
        listaPredmeta = new ArrayList<>();
        listaPredmeta.addAll(copyListaPredmeta);
        brojac++;
        System.out.println("naziv smera je " + smer.getNaziv());
    }

    public void saveSmer() throws Exception {
        choosePredmet();
        smer.setPredajeList(listaIzabranihPredmeta);
        System.out.println("saveSmer: naziv smera je " + smer.getNaziv());
        if (smer.getNaziv() == null || smer.getSmerID() == null) {
            greska = true;
            uspeh = false;
            FacesContext.getCurrentInstance().addMessage("greska", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Popunite sva polja za unos smera", ""));
        } else if (smer.getPredajeList().isEmpty()) {
            greska = true;
            uspeh = false;
            FacesContext.getCurrentInstance().addMessage("greska", new FacesMessage(FacesMessage.SEVERITY_ERROR, "noste uneli niti jedan predmet za smer", ""));
        } else {
            if (sBSmer.saveSmer(smer)) {
//            FacesContext.getCurrentInstance().addMessage("uspeh", new FacesMessage("Smer je sacuvan"));
                FacesContext.getCurrentInstance().addMessage("greska", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Smer je sacuvan", ""));
                greska = false;
                uspeh = true;
            } else {
                greska = true;
                uspeh = false;
                FacesContext.getCurrentInstance().addMessage("greska", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Doslo je do greske i smer nije sacuvan", ""));
            }
        }
    }

    public boolean text1() {
        if (brojac == 1) {
            return true;
        }
        return false;
    }

    public boolean text2() {
        if (brojac == 2) {
            return true;
        }
        return false;
    }

    public boolean text3() {
        if (brojac == 3) {
            return true;
        }
        return false;
    }

    public boolean text4() {
        if (brojac == 4) {
            return true;
        }
        return false;
    }

    public boolean button() {
        if (brojac < 4) {
            return false;
        } else {
            return true;
        }
    }

}
