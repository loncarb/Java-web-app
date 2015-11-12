/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.student;

import domen.Student;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface SBStudentLocal {
    public Student getStudent(int jbmg);
    public List<Student> getStudent();
    public List<Student> searchStudent(Student s);
    public boolean changeStudent(Student s, int i) throws Exception;
    public boolean saveStudent(Student s) throws Exception;
    public boolean deleteStudent(Student s)throws Exception;
}
