/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.sluzbenik;

import domen.Sluzbenik;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface SBSluzbenikLocal {
    public Sluzbenik getSluzbenik(String korisnickoIme);
}
