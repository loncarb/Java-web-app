/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.mesto;

import domen.Mesto;
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
public class SBMesto implements SBMestoLocal {

//    @PersistenceContext(unitName = "EJBStudentskaSluzbaIspitiPU")
//    private EntityManager em;
    private static SBMesto instance;
    private EntityManagerFactory emf;
    
    public SBMesto(){
        emf = Persistence.createEntityManagerFactory("EJBStudentskaSluzbaIspitiPU");
    }
    
    public static SBMesto getinstance() {
        if (instance == null){
            instance = new SBMesto();
        }
        return instance;
    }
    
    @Override
    public Mesto getMesto(int id) {
        EntityManager em = emf.createEntityManager();
        Mesto mesto = null;
        try{
            mesto = em.find(Mesto.class, id);
            if(mesto == null){
                System.out.println("mestoje null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            em.close();
            return mesto;
        }
    }

    @Override
    public List<Mesto> getMesto() {
        EntityManager em = emf.createEntityManager();
        List<Mesto> lista = new ArrayList<>();
        try{
            lista = em.createNamedQuery("Mesto.findAll", Mesto.class).getResultList();
            if (lista == null){
                System.out.println("lista mesta je null");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            em.close();
            return lista;
        }
    }
}
