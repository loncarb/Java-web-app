/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.predaje;

import domen.Predaje;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface SBPredajeLocal {
    public Predaje getPredaje(int id);
    public List<Predaje> getPredaje();
    public void savePredaje(Predaje p) throws Exception;
    public int getPredajeID();
}
