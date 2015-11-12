/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import domen.Mesto;
import domen.Smer;
import domen.Student;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sb.mesto.SBMestoLocal;
import sb.smer.SBSmerLocal;
import sb.student.SBStudent;
import sb.student.SBStudentLocal;

/**
 *
 * @author user
 */
@ManagedBean
@ViewScoped
public class MBNoviStudent {

    boolean greska;
    boolean uspeh;
    Student noviStudent;
    Student studentZaIzmenu;
    List<Mesto> listaMesta;
    List<Smer> listaSmerova;
    Mesto trenutnoMesto;
    Smer trenutniSmer;
    @ManagedProperty(value = "#{mBSesija}")
    MBSesija sesija;
    @EJB
    SBMestoLocal sBMesto;
    @EJB
    SBStudentLocal sBStudent;
    @EJB
    SBSmerLocal sBSmer;
    int a;
    int jmbg;

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

    public int getJmbg() {
        return jmbg;
    }

    public void setJmbg(int jmbg) {
        this.jmbg = jmbg;
    }

    public Student getStudentZaIzmenu() {
        return studentZaIzmenu;
    }

    public void setStudentZaIzmenu(Student studentZaIzmenu) {
        this.studentZaIzmenu = studentZaIzmenu;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public List<Smer> getListaSmerova() {
        return listaSmerova;
    }

    public void setListaSmerova(List<Smer> listaSmerova) {
        this.listaSmerova = listaSmerova;
    }

    public Smer getTrenutniSmer() {
        return trenutniSmer;
    }

    public void setTrenutniSmer(Smer trenutniSmer) {
        this.trenutniSmer = trenutniSmer;
    }

    public SBSmerLocal getsBSmer() {
        return sBSmer;
    }

    public void setsBSmer(SBSmerLocal sBSmer) {
        this.sBSmer = sBSmer;
    }

    public MBSesija getSesija() {
        return sesija;
    }

    public void setSesija(MBSesija sesija) {
        this.sesija = sesija;
    }

    public SBMestoLocal getsBMesto() {
        return sBMesto;
    }

    public void setsBMesto(SBMestoLocal sBMesto) {
        this.sBMesto = sBMesto;
    }

    public SBStudentLocal getsBStudent() {
        return sBStudent;
    }

    public void setsBStudent(SBStudent sBStudent) {
        this.sBStudent = sBStudent;
    }

    public Student getNoviStudent() {
        return noviStudent;
    }

    public void setNoviStudent(Student noviStudent) {
        this.noviStudent = noviStudent;
    }

    public List<Mesto> getListaMesta() {
        return listaMesta;
    }

    public void setListaMesta(List<Mesto> listaMesta) {
        this.listaMesta = listaMesta;
    }

    public Mesto getTrenutnoMesto() {
        return trenutnoMesto;
    }

    public void setTrenutnoMesto(Mesto trenutnoMesto) {
        this.trenutnoMesto = trenutnoMesto;
    }

    public MBNoviStudent() {

    }

    @PostConstruct
    public void postConstruct() {
        noviStudent = new Student();
        trenutnoMesto = new Mesto();
        listaMesta = sBMesto.getMesto();
        listaSmerova = sBSmer.getSmer();
        studentZaIzmenu = new Student();
        greska = false;
        uspeh = false;
        if (sesija.getStudentZaIzmenu().getJmbg() == null) {

        } else {
            noviStudent = sesija.getStudentZaIzmenu();
            a = noviStudent.getJmbg();
            trenutnoMesto = sesija.getStudentZaIzmenu().getMesto();
//            studentZaIzmenu.setJmbg(sesija.getStudentZaIzmenu().getJmbg());
            sesija.setStudentZaIzmenu(new Student());
        }
    }

    public void saveStudenta() throws Exception {
        if (noviStudent.getBroj() == null || noviStudent.getBrojIndeksa() == null || noviStudent.getIme() == null || noviStudent.getJmbg() == null
                || noviStudent.getMesto() == null || noviStudent.getPrezime() == null || noviStudent.getSifra() == null || noviStudent.getSmer() == null
                || noviStudent.getUlica() == null) {
            greska = true;
            uspeh = false;
            FacesContext.getCurrentInstance().addMessage("greska", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Popunite sva polja za unos studenta", ""));
        } else {

            noviStudent.setSluzbenik(sesija.getUlogovaniSluzbenik());
            if (sBStudent.saveStudent(noviStudent)) {
                FacesContext.getCurrentInstance().addMessage("uspeh", new FacesMessage("Student je sacuvan"));
                greska = false;
                uspeh = true;
            } else {
                greska = true;
                uspeh = false;
                FacesContext.getCurrentInstance().addMessage("greska", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Student vec postoji u bazi", ""));
            }
        }

    }

    public void updateStudent() {
        try {
            System.out.println("usao u update");
            noviStudent.setSluzbenik(sesija.getUlogovaniSluzbenik());
            System.out.println("a u update je" + a);
            if (noviStudent.getBroj() == null || noviStudent.getBrojIndeksa() == null || noviStudent.getIme() == null || noviStudent.getJmbg() == null
                    || noviStudent.getMesto() == null || noviStudent.getPrezime() == null || noviStudent.getSifra() == null || noviStudent.getSmer() == null
                    || noviStudent.getUlica() == null) {
                greska = true;
                uspeh = false;
                FacesContext.getCurrentInstance().addMessage("greska", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Popunite sva polja za unos studenta", ""));
            } else {
                if (sBStudent.changeStudent(noviStudent, a)) {
                    System.out.println("sacuvao");
                    FacesContext.getCurrentInstance().addMessage("uspeh", new FacesMessage("Student je sacuvan"));
                    greska = false;
                    uspeh = true;
                } else {
                    greska = true;
                    uspeh = false;
                    FacesContext.getCurrentInstance().addMessage("greska", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Doslo je do greske i student nije sacuvan", ""));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public boolean buttonSave() {
        if (studentZaIzmenu.getJmbg() == null) {
            return true;
        }
        return false;
    }
}
