/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.ispitnirok;

import domen.Ispitnirok;
import domen.Ispitnirok_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author user
 */
@Stateless
public class SBIspitniRok implements SBIspitniRokLocal {

    private static SBIspitniRok instance;
    private EntityManagerFactory emf;
//    @PersistenceContext(unitName = "EJBStudentskaSluzbaIspitiPU")
//    private EntityManager em;
    
    public SBIspitniRok() {
        emf = Persistence.createEntityManagerFactory("EJBStudentskaSluzbaIspitiPU");
    }
    
    @Override
    public Ispitnirok getIspitniRok(int id) {
        EntityManager em = emf.createEntityManager();
        Ispitnirok rok = null;
        try{
            rok = em.find(Ispitnirok.class, id);
            if(rok == null){
                System.out.println("rok je null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            em.close();
            return rok;
        }
    }

    @Override
    public List<Ispitnirok> getIspitiRok() {
        EntityManager em = emf.createEntityManager();
        List<Ispitnirok> lista = new ArrayList<>();
        try{
            lista = em.createNamedQuery("Ispitnirok.findAll", Ispitnirok.class).getResultList();
            if (lista == null){
                System.out.println("lista je null");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            em.close();
            return lista;
        }
    }
    
    public static SBIspitniRok getInstance() {
        if(instance == null){
            instance = new SBIspitniRok();
        }
        return instance;
    }
}
