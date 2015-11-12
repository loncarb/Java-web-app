/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.statusPrijave;

import domen.Statusprijave;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import sb.smer.SBSmer;

/**
 *
 * @author user
 */
@Stateless
public class SBStuatusPrijave implements SBStuatusPrijaveLocal {

//    @PersistenceContext(unitName = "EJBStudentskaSluzbaIspitiPU")
//    private EntityManager em;
    private static SBStuatusPrijave instance;
    private EntityManagerFactory emf;
    
    public SBStuatusPrijave(){
        emf = Persistence.createEntityManagerFactory("EJBStudentskaSluzbaIspitiPU");
    }
    
    public static SBStuatusPrijave getinstance() {
        if (instance == null){
            instance = new SBStuatusPrijave();
        }
        return instance;
    }
    
    @Override
    public Statusprijave getStatusPrijava(int id) {
        EntityManager em = emf.createEntityManager();
        Statusprijave statusPrijave = null;
        try{
            statusPrijave = em.find(Statusprijave.class, id);
            if(statusPrijave == null){
                System.out.println("rok je null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            em.close();
            return statusPrijave;
        }
    }

    @Override
    public List<Statusprijave> getStatusPrijave() {
        EntityManager em = emf.createEntityManager();
        List<Statusprijave> lista = new ArrayList<>();
        try{
            lista = em.createNamedQuery("Statusprijave.findAll", Statusprijave.class).getResultList();
            if (lista == null){
                System.out.println("lista statusa prijava je null");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            em.close();
            return lista;
        }
    }
}
