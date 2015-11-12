/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.profesor;

import domen.Profesor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import sb.predmet.SBPredmet;

/**
 *
 * @author user
 */
@Stateless
public class SBProfesor implements SBProfesorLocal {

//    @PersistenceContext(unitName = "EJBStudentskaSluzbaIspitiPU")
//    private EntityManager em;
    private static SBProfesor instance;
    private EntityManagerFactory emf;
    
    public SBProfesor(){
        emf = Persistence.createEntityManagerFactory("EJBStudentskaSluzbaIspitiPU");
    }
    
    public static SBProfesor getinstance() {
        if (instance == null){
            instance = new SBProfesor();
        }
        return instance;
    }
    
    @Override
    public Profesor getProfesor(int jmbg) {
        EntityManager em = emf.createEntityManager();
        Profesor profesor = null;
        try{
            profesor = em.find(Profesor.class, jmbg);
            if(profesor == null){
                System.out.println("profesor je null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            em.close();
            return profesor;
        }
    }

}
