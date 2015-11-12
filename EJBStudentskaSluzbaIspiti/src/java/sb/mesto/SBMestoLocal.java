/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.mesto;

import domen.Mesto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface SBMestoLocal {
    public Mesto getMesto (int id);
    public List<Mesto> getMesto();
}
