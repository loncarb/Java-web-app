/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import domen.Statusprijave;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface StatusprijaveFacadeLocal {

    void create(Statusprijave statusprijave);

    void edit(Statusprijave statusprijave);

    void remove(Statusprijave statusprijave);

    Statusprijave find(Object id);

    List<Statusprijave> findAll();

    List<Statusprijave> findRange(int[] range);

    int count();
    
}
