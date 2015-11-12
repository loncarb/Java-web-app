/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import domen.Ispitnirok;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface IspitnirokFacadeLocal {

    void create(Ispitnirok ispitnirok);

    void edit(Ispitnirok ispitnirok);

    void remove(Ispitnirok ispitnirok);

    Ispitnirok find(Object id);

    List<Ispitnirok> findAll();

    List<Ispitnirok> findRange(int[] range);

    int count();
    
}
