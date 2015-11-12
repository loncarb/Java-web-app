/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.sluzbenik;

import domen.Sluzbenik;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.hibernate.Session;

/**
 *
 * @author user
 */
@Stateless
public class SBSluzbenik implements SBSluzbenikLocal {

//    @PersistenceContext(unitName = "EJBStudentskaSluzbaIspitiPU", type = PersistenceContextType.EXTENDED)
//    private EntityManager em;
    private static SBSluzbenik instance;
    private EntityManagerFactory emf;
    
    public SBSluzbenik(){
        emf = Persistence.createEntityManagerFactory("EJBStudentskaSluzbaIspitiPU");
    }
    
    @Override
    public Sluzbenik getSluzbenik(String korisnickoIme) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBStudentskaSluzbaIspitiPU");
        EntityManager em = null;
        Sluzbenik sluzbenik = null;
        try{
            em = emf.createEntityManager();
            if (em==null){
                System.out.println("em je null");
            }
            sluzbenik = em.find(Sluzbenik.class, korisnickoIme);
            if(sluzbenik == null){
                System.out.println("sluzbenik je null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            em.close();
//            emf.close();
            return sluzbenik;
        }
    }
    
    public SBSluzbenik getinstance() {
        if (instance == null){
            instance = new SBSluzbenik();
        }
        return instance;
    }

}
