/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.statusPrijave;

import domen.Statusprijave;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface SBStuatusPrijaveLocal {
    public Statusprijave getStatusPrijava(int id);
    public List<Statusprijave> getStatusPrijave();
}
