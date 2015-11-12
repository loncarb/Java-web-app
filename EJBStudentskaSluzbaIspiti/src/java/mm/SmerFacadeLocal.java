/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import domen.Smer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface SmerFacadeLocal {

    void create(Smer smer);

    void edit(Smer smer);

    void remove(Smer smer);

    Smer find(Object id);

    List<Smer> findAll();

    List<Smer> findRange(int[] range);

    int count();
    
}
