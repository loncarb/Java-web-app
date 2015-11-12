/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import domen.Predmet;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface PredmetFacadeLocal {

    void create(Predmet predmet);

    void edit(Predmet predmet);

    void remove(Predmet predmet);

    Predmet find(Object id);

    List<Predmet> findAll();

    List<Predmet> findRange(int[] range);

    int count();
    
}
