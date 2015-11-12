/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import domen.Mesto;
import domen.Student;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import sb.mesto.SBMestoLocal;
import sb.student.SBStudentLocal;

/**
 *
 * @author user
 */
@ManagedBean
@ViewScoped
public class MBPretragaStudenata {

    boolean greska;
    boolean uspeh;
    List<Student> listaStudenata;
    Student studentZaizmenu;
    Student studentZaPretragu;
    List<Mesto> listaMesta;
    @EJB
    SBMestoLocal sBMesto;
    @EJB
    SBStudentLocal sBStudent;
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

    public SBMestoLocal getsBMesto() {
        return sBMesto;
    }

    public void setsBMesto(SBMestoLocal sBMesto) {
        this.sBMesto = sBMesto;
    }

    public SBStudentLocal getsBStudent() {
        return sBStudent;
    }

    public void setsBStudent(SBStudentLocal sBStudent) {
        this.sBStudent = sBStudent;
    }

    public List<Student> getListaStudenata() {
        return listaStudenata;
    }

    public void setListaStudenata(List<Student> listaStudenata) {
        this.listaStudenata = listaStudenata;
    }

    public Student getStudentZaizmenu() {
        return studentZaizmenu;
    }

    public void setStudentZaizmenu(Student studentZaizmenu) {
        this.studentZaizmenu = studentZaizmenu;
    }

    public Student getStudentZaPretragu() {
        return studentZaPretragu;
    }

    public void setStudentZaPretragu(Student studentZaPretragu) {
        this.studentZaPretragu = studentZaPretragu;
    }

    public List<Mesto> getListaMesta() {
        return listaMesta;
    }

    public void setListaMesta(List<Mesto> listaMesta) {
        this.listaMesta = listaMesta;
    }

    public MBSesija getSesija() {
        return sesija;
    }

    public void setSesija(MBSesija sesija) {
        this.sesija = sesija;
    }

    public MBPretragaStudenata() {
        studentZaizmenu = new Student();
        studentZaPretragu = new Student();
        
    }

    @PostConstruct
    public void postConstruct() {
        listaMesta = sBMesto.getMesto();
        listaStudenata = sBStudent.getStudent();
        greska = false;
        uspeh = false;
    }

    public String izmeniStudenta() {
        if (studentZaizmenu == null) {
            FacesContext.getCurrentInstance().addMessage("greska", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste izabrali studenta", ""));
            return null;
        }
        sesija.setStudentZaIzmenu(studentZaizmenu);
        System.out.println("student za izmenu je "+studentZaizmenu.getJmbg() +" " +sesija.getStudentZaIzmenu().getJmbg());
        return "/zaSluzbenika/izmenaStudenta";
    }

    public void deleteStudent() throws Exception {
        System.out.println("usao u metodu delete");
        if (studentZaizmenu == null) {

            FacesContext.getCurrentInstance().addMessage("greska",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste izabrali studenta", ""));
        }
        Student s = studentZaizmenu;
        studentZaizmenu = new Student();
        if (sBStudent.deleteStudent(s)) {
            listaStudenata = sBStudent.getStudent();
            FacesContext.getCurrentInstance().addMessage("uspeh",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Student je obrisan", ""));
            greska = false;
            uspeh = true;
        } else {
            FacesContext.getCurrentInstance().addMessage("greska",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "doslo je do greske, student nije obrisan", ""));
            greska = true;
            uspeh = false;
        }
    }

    public void nadjiStudenta() {
        System.out.println("usao u search pre try");
        try {
            System.out.println("usao u metodu search");
            System.out.println("jmbg: "+studentZaPretragu.getJmbg());
            System.out.println("mesto: "+studentZaPretragu.getMesto());
            System.out.println("parametri za pretragu su: ime: "+studentZaPretragu.getIme());
            System.out.println("prezime: "+studentZaPretragu.getPrezime());
            
            listaStudenata = sBStudent.searchStudent(studentZaPretragu);
            System.out.println("lista je vracena");
            if (listaStudenata == null) {
                System.out.println("lista studenata je null");
            } else {
                if (listaStudenata.isEmpty()) {
                    System.out.println("lista studenata je prazna");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }   
    }
    
    public boolean uspeh() {
        System.out.println("usao u metodu uspeh");
        if(uspeh == true){
            return true;
        }
        return false;
    }
    
    public boolean greska() {
        System.out.println("usao u metodu greska");
        if(greska == true){
            return true;
        }
        return false;
    }
}
