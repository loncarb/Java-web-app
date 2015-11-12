/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import domen.Statusprijave;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author user
 */
@Stateless
public class StatusprijaveFacade extends AbstractFacade<Statusprijave> implements StatusprijaveFacadeLocal {
    @PersistenceContext(unitName = "EJBStudentskaSluzbaIspitiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatusprijaveFacade() {
        super(Statusprijave.class);
    }
    
}
