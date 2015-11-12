/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import domen.Mesto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface MestoFacadeLocal {

    void create(Mesto mesto);

    void edit(Mesto mesto);

    void remove(Mesto mesto);

    Mesto find(Object id);

    List<Mesto> findAll();

    List<Mesto> findRange(int[] range);

    int count();
    
}
