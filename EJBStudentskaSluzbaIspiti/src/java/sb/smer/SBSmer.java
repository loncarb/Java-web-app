/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.smer;

import domen.Predaje;
import domen.Smer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import sb.predaje.SBPredaje;
import sb.profesor.SBProfesor;

/**
 *
 * @author user
 */
@Stateless
public class SBSmer implements SBSmerLocal {

//    @PersistenceContext(unitName = "EJBStudentskaSluzbaIspitiPU")
//    private EntityManager em;
    private static SBSmer instance;
    private EntityManagerFactory emf;

    public SBSmer() {
        emf = Persistence.createEntityManagerFactory("EJBStudentskaSluzbaIspitiPU");
    }

    public static SBSmer getinstance() {
        if (instance == null) {
            instance = new SBSmer();
        }
        return instance;
    }

    @Override
    public Smer getSmer(int id) {
        EntityManager em = emf.createEntityManager();
        Smer smer = null;
        try {
            smer = em.find(Smer.class, id);
            if (smer == null) {
                System.out.println("rok je null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            return smer;
        }
    }

    @Override
    public List<Smer> getSmer() {
        EntityManager em = emf.createEntityManager();
        List<Smer> lista = new ArrayList<>();
        try {
            lista = em.createNamedQuery("Smer.findAll", Smer.class).getResultList();
            if (lista == null) {
                System.out.println("lista smerova je null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            return lista;
        }
    }

    @Override
    public boolean saveSmer(Smer s) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            SBPredaje sBPredaje = new SBPredaje();
            Smer smerDb = em.find(Smer.class, s.getSmerID());
            if (smerDb == null) {
                System.out.println("EJB: naziv smera je "+s.getNaziv()+" id smera je "+s.getSmerID());
                if(s.getPredajeList().isEmpty()){
                    System.out.println("lista PREDAJE je prazna");
                }else{
                    for(Predaje p: s.getPredajeList()){
                        System.out.println("id "+p.getPredajeID()+" godina "+p.getGodinaStudija()+"; ");
                    }
                }
                int b = 0;
                b = sBPredaje.getPredajeID();
                for(Predaje predaje : s.getPredajeList()){
                    predaje.setPredajeID(b);
                    b++;
                }
                em.persist(s);
//                for(Predaje predaje : s.getPredajeList()){
//                    sBPredaje.savePredaje(predaje);
//                }
                
                em.getTransaction().commit();
                em.close();
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public int getSmerID() {
        EntityManager em = emf.createEntityManager();
        List<Smer> lista = new ArrayList<>();
        int id = -1;
        try {
            lista = em.createQuery("SELECT s FROM Smer s ORDER BY s.smerID desc").getResultList();
            if (lista == null) {
                System.out.println("lista smerova je null");
            }
            id = lista.get(0).getSmerID()+ 1;
            return id;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }
}
