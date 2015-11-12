/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import domen.Ispitnirok;
import domen.Predaje;
import domen.Predmet;
import domen.Prijava;
import domen.Statusprijave;
import domen.Student;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import sb.ispitnirok.SBIspitniRokLocal;
import sb.predaje.SBPredajeLocal;
import sb.predmet.SBPredmet;
import sb.predmet.SBPredmetLocal;
import sb.prijava.SBPrijavaLocal;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class MBPrijaviIspite {

    boolean greska;
    boolean uspeh;
    @EJB
    SBPredajeLocal sBPredaje;
    List<Predmet> listaPredmeta;
    List<Prijava> listaIzabranihPredmeta;
    Student student;
    Predmet predmet;
    @EJB
    SBPrijavaLocal sBPrijava;
    @EJB
    SBPredmetLocal sBPredmet;
    @ManagedProperty(value = "#{mBSesija}")
    MBSesija sesija;
    List<Ispitnirok> listaRokova;
    @EJB
    SBIspitniRokLocal sBIspitniRok;
    Ispitnirok izabraniIspitniRok;

    public SBPredajeLocal getsBPredaje() {
        return sBPredaje;
    }

    public void setsBPredaje(SBPredajeLocal sBPredaje) {
        this.sBPredaje = sBPredaje;
    }

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

    public List<Ispitnirok> getListaRokova() {
        return listaRokova;
    }

    public void setListaRokova(List<Ispitnirok> listaRokova) {
        this.listaRokova = listaRokova;
    }

    public SBIspitniRokLocal getsBIspitniRok() {
        return sBIspitniRok;
    }

    public void setsBIspitniRok(SBIspitniRokLocal sBIspitniRok) {
        this.sBIspitniRok = sBIspitniRok;
    }

    public Ispitnirok getIzabraniIspitniRok() {
        return izabraniIspitniRok;
    }

    public void setIzabraniIspitniRok(Ispitnirok izabraniIspitniRok) {
        this.izabraniIspitniRok = izabraniIspitniRok;
    }

    public SBPrijavaLocal getsBPrijava() {
        return sBPrijava;
    }

    public void setsBPrijava(SBPrijavaLocal sBPrijava) {
        this.sBPrijava = sBPrijava;
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

    public List<Prijava> getListaIzabranihPredmeta() {
        return listaIzabranihPredmeta;
    }

    public void setListaIzabranihPredmeta(List<Prijava> listaIzabranihPredmeta) {
        this.listaIzabranihPredmeta = listaIzabranihPredmeta;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

    /**
     * Creates a new instance of MBPrijaviIspite
     */
    public MBPrijaviIspite() {
    }

    @PostConstruct
    public void postConstruct() {
        student = sesija.getUlogovaniStudent();
        listaPredmeta = new ArrayList<>();
        listaIzabranihPredmeta = new ArrayList<>();
        listaRokova = sBIspitniRok.getIspitiRok();
        greska = false;
        uspeh = false;
        List<Prijava> listaPolozenihPrijava = student.getPrijavaList();
        List<Predmet> pom = sBPredmet.getPredmet();
        List<Predaje> listaPredaje = sBPredaje.getPredaje();
        
            for(Predaje predaje: listaPredaje){
                if (predaje.getSmer().getSmerID()==student.getSmer().getSmerID() && student.getGodinaStudija()>=predaje.getGodinaStudija()){
                    listaPredmeta.add(predaje.getPredmet());
                    System.out.println("dodao predmet");
                }
            }
        
        pom.removeAll(pom);
        pom.addAll(listaPredmeta);
        for (Predmet p: pom){
            for(Prijava prijava : listaPolozenihPrijava){
                if(prijava.getPredmet().getPredmetID()==p.getPredmetID()){
                    listaPredmeta.remove(p);
                    System.out.println("obrisao predmet");
                }
            }
        }
        
    }

    public void prijavi() {
        try {
            System.out.println("usao u prijavi()");
            int id = sBPrijava.getPrijavaID();
            if (true) {

                for (Predmet p : listaPredmeta) {
                    System.out.println("usao u petlju");
                    if (p.getPromenljiva()) {
                        Prijava prijava = new Prijava();
                        prijava.setOcena(0);
                        prijava.setPredmet(p);
                        prijava.setStudent(student);
                        Statusprijave status = new Statusprijave(5, "Prijavljen");
                        prijava.setStatus(status);
                        prijava.setPrijavaID(id);
                        prijava.setIspitniRok(izabraniIspitniRok);
                        System.out.println("pre snimanja");
                        sBPrijava.savePrijava(prijava);
                        System.out.println("snimio");
                        id++;
                    }
                }
//            Iterator<Predmet> i = listaPredmeta.iterator();
//            while (i.hasNext()) {
//                Predmet p = i.next();
//                
//            }
                System.out.println("kraj metode");
                greska = false;
                uspeh = true;
                FacesContext.getCurrentInstance().addMessage("uspeh",
                        new FacesMessage("Ispit je prijavljen"));
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage("greska",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ispit nije prijavljen", ""));
            greska = true;
            uspeh = false;
            ex.printStackTrace();
        }
    }
}
