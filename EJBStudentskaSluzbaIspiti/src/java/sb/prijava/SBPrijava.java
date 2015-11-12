/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.prijava;

import domen.Prijava;
import domen.Student;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
@Stateless
public class SBPrijava implements SBPrijavaLocal {

    private static SBPrijava instance;
    private EntityManagerFactory emf;

    public SBPrijava() {
        emf = Persistence.createEntityManagerFactory("EJBStudentskaSluzbaIspitiPU");
    }

    public static SBPrijava getinstance() {
        if (instance == null) {
            instance = new SBPrijava();
        }
        return instance;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Prijava> getPrijava(Student s) {
        EntityManager em = emf.createEntityManager();
        List<Prijava> lista = new ArrayList<>();
        try {
            lista = em.createQuery("SELECT p FROM Prijava p WHERE p.student = :stud AND p.ocena > :ocena").
                    setParameter("stud", s).setParameter("ocena", 5).getResultList();
            if (lista == null) {
                System.out.println("lista prijava je null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            return lista;
        }
    }

    @Override
    public Prijava getPrijava(int i) {
        EntityManager em = emf.createEntityManager();
        Prijava prijava = null;
        try {
            prijava = em.find(Prijava.class, i);
            if (prijava == null) {
                System.out.println("rok je null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            return prijava;
        }
    }

    @Override
    public boolean savePrijava(Prijava p) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Prijava prijavaDb = em.find(Prijava.class, p.getPrijavaID());
            if (prijavaDb == null){
                em.persist(p);
                em.getTransaction().commit();
                em.close();
                return true;
            }else{
                return false;                
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public int getPrijavaID() {
        EntityManager em = emf.createEntityManager();
        List<Prijava> lista = new ArrayList<>();
        int id = -1;
        try {
            lista = em.createQuery("SELECT p FROM Prijava p ORDER BY p.prijavaID desc").getResultList();
            if (lista == null) {
                System.out.println("lista prijava je null");
            }
            id = lista.get(0).getPrijavaID() + 1;
            return id;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }
}
