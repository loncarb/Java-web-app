/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.smer;

import domen.Smer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface SBSmerLocal {
    public Smer getSmer(int id);
    public List<Smer> getSmer();
    public boolean saveSmer(Smer s);
    public int getSmerID();
}
