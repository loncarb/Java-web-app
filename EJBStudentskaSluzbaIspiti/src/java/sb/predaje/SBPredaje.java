/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.predaje;

import domen.Predaje;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import sb.mesto.SBMesto;

/**
 *
 * @author user
 */
@Stateless
public class SBPredaje implements SBPredajeLocal {

//    @PersistenceContext(unitName = "EJBStudentskaSluzbaIspitiPU")
//    private EntityManager em;
    private static SBPredaje instance;
    private EntityManagerFactory emf;
    
    public SBPredaje(){
        emf = Persistence.createEntityManagerFactory("EJBStudentskaSluzbaIspitiPU");
    }
    
    public static SBPredaje getinstance() {
        if (instance == null){
            instance = new SBPredaje();
        }
        return instance;
    }
    
    @Override
    public Predaje getPredaje(int id) {
        EntityManager em = emf.createEntityManager();
        Predaje predaje = null;
        try{
            predaje = em.find(Predaje.class, id);
            if(predaje == null){
                System.out.println("rok je null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            em.close();
            return predaje;
        }
    }

    @Override
    public List<Predaje> getPredaje() {
        EntityManager em = emf.createEntityManager();
        List<Predaje> lista = new ArrayList<>();
        try{
            lista = em.createNamedQuery("Predaje.findAll", Predaje.class).getResultList();
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

    @Override
    public void savePredaje(Predaje p) throws Exception {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
//            Predaje predDb = em.find(Predaje.class, p.getPredajeID());
//            if (predDb == null){
//                int predajeID = getPredajeID();
//                p.setPredajeID(predajeID);
                em.persist(p);
//            }else{
//                throw new Exception("Predaje vec postoji u bazi");
//            }
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            em.close();
        }
    }

    @Override
    public int getPredajeID() {
        EntityManager em = emf.createEntityManager();
        List<Predaje> lista = new ArrayList<>();
        int id = -1;
        try {
            lista = em.createQuery("SELECT p FROM Predaje p ORDER BY p.predajeID desc").getResultList();
            if (lista == null) {
                System.out.println("lista smerova je null");
            }
            if(lista.isEmpty()){
                id = 1;
            }else{
                id = lista.get(0).getPredajeID()+ 1;
            }
            return id;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }
}
