/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import domen.Mesto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author user
 */
@Stateless
public class MestoFacade extends AbstractFacade<Mesto> implements MestoFacadeLocal {
    @PersistenceContext(unitName = "EJBStudentskaSluzbaIspitiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MestoFacade() {
        super(Mesto.class);
    }
    
}
