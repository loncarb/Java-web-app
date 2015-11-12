/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.ispitnirok;

import domen.Ispitnirok;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface SBIspitniRokLocal {
    public Ispitnirok getIspitniRok (int id);
    public List<Ispitnirok> getIspitiRok();
}
