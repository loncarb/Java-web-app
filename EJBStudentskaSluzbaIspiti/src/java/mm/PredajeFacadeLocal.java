/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import domen.Predaje;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface PredajeFacadeLocal {

    void create(Predaje predaje);

    void edit(Predaje predaje);

    void remove(Predaje predaje);

    Predaje find(Object id);

    List<Predaje> findAll();

    List<Predaje> findRange(int[] range);

    int count();
    
}
