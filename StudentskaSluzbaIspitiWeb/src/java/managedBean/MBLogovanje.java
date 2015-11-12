/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import domen.Sluzbenik;
import domen.Student;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import sb.sluzbenik.SBSluzbenikLocal;
import sb.student.SBStudentLocal;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class MBLogovanje {

    private Sluzbenik ulogovaniSluzbenik;
    boolean greska;
    boolean uspeh;
    @EJB
    SBStudentLocal sbStudent;
    
    @EJB
    SBSluzbenikLocal sbSluzbenik;
            
    @ManagedProperty(value = "#{mBsesija}")
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

    public MBSesija getSesija() {
        return sesija;
    }

    public void setSesija(MBSesija sesija) {
        this.sesija = sesija;
    }

    public Sluzbenik getUlogovaniSluzbenik() {
        return ulogovaniSluzbenik;
    }

    public void setUlogovaniSluzbenik(Sluzbenik ulogovaniSluzbenik) {
        this.ulogovaniSluzbenik = ulogovaniSluzbenik;
    }
    
    public MBLogovanje() {
        System.out.println("mbLogovanje konstruktor");
    }
    
    @PostConstruct
    public void postConstruct() {
        ulogovaniSluzbenik = new Sluzbenik();
        System.out.println("mbLogovanje postContruct");
        greska = false;
        uspeh = false;
    }
    
    public String logovanje(){
        sesija = new MBSesija();
        if(ulogovaniSluzbenik != null) {
            Sluzbenik s = sbSluzbenik.getSluzbenik(ulogovaniSluzbenik.getKorisnickoIme());
            if(sesija == null){
                System.out.println("sesija je null");
            }
            if (s != null){
                if(ulogovaniSluzbenik.getKorisnickaSifra().equals(s.getKorisnickaSifra())){
                    sesija.setUlogovaniSluzbenik(s);
                    sesija.setUlogovaniStudent(null);
                    return "welcome";
                }else{
                    FacesContext.getCurrentInstance().addMessage("greska", 
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pogresno uneta sifra", ""));
                    greska = true;
                }
            }else{
                Student stud = sbStudent.getStudent(Integer.parseInt(ulogovaniSluzbenik.getKorisnickoIme()));
                if (stud != null){
                    if (stud.getSifra().equals(s.getKorisnickaSifra())){
                        sesija.setUlogovaniStudent(stud);
                        sesija.setUlogovaniSluzbenik(null);
                        return "welcome";
                    }
                }
            }
        }
        return ".index.xhtml";
    }
}
