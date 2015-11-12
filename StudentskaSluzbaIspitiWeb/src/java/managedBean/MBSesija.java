/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import domen.Sluzbenik;
import domen.Student;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import sb.sluzbenik.SBSluzbenikLocal;
import sb.student.SBStudentLocal;

/**
 *
 * @author user
 */
@ManagedBean
@SessionScoped
public class MBSesija implements Serializable {

    private Student studentZaIzmenu;
    private Sluzbenik ulogovaniSluzbenik;
    private Student ulogovaniStudent;
    private int izabraniJezik;
    private static final int LATINCA=1;
    private static final int CIRILICA=2;
    @EJB
    SBStudentLocal sbStudent;
    @EJB
    SBSluzbenikLocal sbSluzbenik;

    public Student getStudentZaIzmenu() {
        return studentZaIzmenu;
    }

    public void setStudentZaIzmenu(Student studentZaIzmenu) {
        this.studentZaIzmenu = studentZaIzmenu;
    }

    public SBStudentLocal getSbStudent() {
        return sbStudent;
    }

    public void setSbStudent(SBStudentLocal sbStudent) {
        this.sbStudent = sbStudent;
    }

    public SBSluzbenikLocal getSbSluzbenik() {
        return sbSluzbenik;
    }

    public void setSbSluzbenik(SBSluzbenikLocal sbSluzbenik) {
        this.sbSluzbenik = sbSluzbenik;
    }

    public int getIzabraniJezik() {
        return izabraniJezik;
    }

    public void setIzabraniJezik(int izabraniJezik) {
        this.izabraniJezik = izabraniJezik;
    }

    public Student getUlogovaniStudent() {
        return ulogovaniStudent;
    }

    public void setUlogovaniStudent(Student ulogovaniStudent) {
        this.ulogovaniStudent = ulogovaniStudent;
    }
    
    public Sluzbenik getUlogovaniSluzbenik() {
        return ulogovaniSluzbenik;
    }

    public void setUlogovaniSluzbenik(Sluzbenik ulogovaniSluzbenik) {
        this.ulogovaniSluzbenik = ulogovaniSluzbenik;
    }
   
    public MBSesija() {
        System.out.println("konstruktor sesija");
    }
    
    @PostConstruct
    public void postConstruct(){
        studentZaIzmenu = new Student();
        izabraniJezik = LATINCA;
        ulogovaniSluzbenik = new Sluzbenik();
        System.out.println("postConstruct sesija");
    }
    
    public void changeLanguage(int i){
        izabraniJezik = i;
        if (izabraniJezik==LATINCA){
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));
        } else {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("sr","SR"));
        }
    }
    
    public boolean menuForStudent(){
        if(ulogovaniStudent.getJmbg() == null){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean menuForSluzbenik(){
        if(ulogovaniSluzbenik.getKorisnickoIme() == null){
            return false;
        }else{
            return true;
        }
    }
    
    public String logovanje(){
        if(ulogovaniSluzbenik != null) {
            Sluzbenik s = sbSluzbenik.getSluzbenik(ulogovaniSluzbenik.getKorisnickoIme());
            
            if (s != null){
                if(ulogovaniSluzbenik.getKorisnickaSifra().equals(s.getKorisnickaSifra())){
                    ulogovaniSluzbenik = s;
                    Student student = new Student();
                    setUlogovaniStudent(student);
                    return "welcome";
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, 
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pogresno uneta sifra", ""));
                }
            }else{
                Student stud = sbStudent.getStudent(Integer.parseInt(ulogovaniSluzbenik.getKorisnickoIme()));
                if (stud != null){
                    if (stud.getSifra().equals(ulogovaniSluzbenik.getKorisnickaSifra())){
                        ulogovaniStudent = stud;
                        Sluzbenik sluzbenik = new Sluzbenik();
                        ulogovaniSluzbenik = sluzbenik;
                        System.out.println("ovde");
                        return "welcome";
                    }
                }
            }
        }
        return "index";
    }
    
    public String logOut() {
        ulogovaniSluzbenik = new Sluzbenik();
        ulogovaniStudent = new Student();
        studentZaIzmenu = new Student();
        izabraniJezik = LATINCA;
        return "/index";
    }
}
