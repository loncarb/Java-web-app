/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.predmet;

import domen.Predmet;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import sb.predaje.SBPredaje;

/**
 *
 * @author user
 */
@Stateless
public class SBPredmet implements SBPredmetLocal {

//    @PersistenceContext(unitName = "EJBStudentskaSluzbaIspitiPU")
//    private EntityManager em;
    private static SBPredmet instance;
    private EntityManagerFactory emf;

    public SBPredmet() {
        emf = Persistence.createEntityManagerFactory("EJBStudentskaSluzbaIspitiPU");
    }

    public static SBPredmet getinstance() {
        if (instance == null) {
            instance = new SBPredmet();
        }
        return instance;
    }

    @Override
    public Predmet getPredmet(int id) {
        EntityManager em = emf.createEntityManager();
        Predmet predmet = null;
        try {
            predmet = em.find(Predmet.class, id);
            if (predmet == null) {
                System.out.println("rok je null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            return predmet;
        }
    }

    @Override
    public List<Predmet> getPredmet() {
        EntityManager em = emf.createEntityManager();
        List<Predmet> lista = new ArrayList<>();
        try {
            lista = em.createNamedQuery("Predmet.findAll", Predmet.class).getResultList();
            if (lista == null) {
                System.out.println("lista predmeta je null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            return lista;
        }
    }

    @Override
    public boolean savePredmet(Predmet p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Predmet predDb = em.find(Predmet.class, p.getPredmetID());
            if (predDb == null) {
                em.persist(p);
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
    public int getPredmetID() {
        EntityManager em = emf.createEntityManager();
        List<Predmet> lista = new ArrayList<>();
        int id = -1;
        try {
            lista = em.createQuery("SELECT p FROM Predmet p ORDER BY p.predmetID desc").getResultList();
            if (lista == null) {
                System.out.println("lista prijava je null");
            }
            id = lista.get(0).getPredmetID()+ 1;
            return id;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }
}
