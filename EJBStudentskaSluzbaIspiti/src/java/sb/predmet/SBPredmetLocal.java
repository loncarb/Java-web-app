/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.predmet;

import domen.Predmet;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface SBPredmetLocal {
    public Predmet getPredmet(int id);
    public List<Predmet> getPredmet();
    public boolean savePredmet(Predmet p);
    public int getPredmetID();
}
