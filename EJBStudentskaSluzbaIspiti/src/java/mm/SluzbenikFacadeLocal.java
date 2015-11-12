/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import domen.Sluzbenik;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface SluzbenikFacadeLocal {

    void create(Sluzbenik sluzbenik);

    void edit(Sluzbenik sluzbenik);

    void remove(Sluzbenik sluzbenik);

    Sluzbenik find(Object id);

    List<Sluzbenik> findAll();

    List<Sluzbenik> findRange(int[] range);

    int count();
    
}
