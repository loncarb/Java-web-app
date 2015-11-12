/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import domen.Prijava;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface PrijavaFacadeLocal {

    void create(Prijava prijava);

    void edit(Prijava prijava);

    void remove(Prijava prijava);

    Prijava find(Object id);

    List<Prijava> findAll();

    List<Prijava> findRange(int[] range);

    int count();
    
}
