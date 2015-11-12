/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.prijava;

import domen.Prijava;
import domen.Student;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface SBPrijavaLocal {
    public List<Prijava> getPrijava(Student s);
    public Prijava getPrijava(int i);
    public boolean savePrijava(Prijava p);
    public int getPrijavaID();
}
