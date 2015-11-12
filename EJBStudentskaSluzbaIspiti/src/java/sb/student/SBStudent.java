/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.student;

import domen.Student;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.queries.QueryByExamplePolicy;
import org.eclipse.persistence.queries.ReadObjectQuery;
import sb.statusPrijave.SBStuatusPrijave;

/**
 *
 * @author user
 */
@Stateless
public class SBStudent implements SBStudentLocal {

//    @PersistenceContext(unitName = "EJBStudentskaSluzbaIspitiPU")
//    private EntityManager em;
    private static SBStudent instance;
    private EntityManagerFactory emf;

    public SBStudent() {
        emf = Persistence.createEntityManagerFactory("EJBStudentskaSluzbaIspitiPU");
    }

    public static SBStudent getinstance() {
        if (instance == null) {
            instance = new SBStudent();
        }
        return instance;
    }

    @Override
    public Student getStudent(int jbmg) {
        EntityManager em = emf.createEntityManager();
        Student student = null;
        try {
            student = em.find(Student.class, jbmg);
            if (student == null) {
                System.out.println("profesor je null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            return student;
        }
    }

    @Override
    public List<Student> getStudent() {
        EntityManager em = emf.createEntityManager();
        List<Student> lista = new ArrayList<>();
        try {
            lista = em.createNamedQuery("Student.findAll", Student.class).getResultList();
            if (lista == null) {
                System.out.println("lista studenata je null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            return lista;
        }
    }

    @Override
    public List<Student> searchStudent(Student s) {
        System.out.println("ovde");
        EntityManager em = emf.createEntityManager();
        List<Student> lista = new ArrayList<>();
        try {
            QueryByExamplePolicy policy = new QueryByExamplePolicy();
            policy.excludeDefaultPrimitiveValues();
            ReadObjectQuery q = new ReadObjectQuery(s, policy);
            Query query = JpaHelper.createQuery(q, em);
            lista = query.getResultList();
            if (lista == null) {
                System.out.println("lista studenata za pretragu je null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            return lista;
        }
    }

    @Override
    public boolean changeStudent(Student s, int i) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            System.out.println("EJB: student za izmenu je "+i);
            Student studDb = em.find(Student.class, i);
            if (studDb == null) {
                System.out.println("EJB: ne postoji student u bazi");
                return false;
            } else {
                System.out.println("uradi merge");
                
//                em.persist(s);
//                studDb.setBroj(s.getBroj());
//                studDb.setBrojIndeksa(s.getBrojIndeksa());
//                studDb.setGodinaStudija(s.getGodinaStudija());
//                studDb.setIme(s.getIme());
//                studDb.setJmbg(s.getJmbg());
                em.merge(s);
//                studDb.setMesto(s.getMesto());
//                studDb.setPrezime(s.getPrezime());
//                studDb = s;
//                em.persist(studDb);
                System.out.println("uradio merge");
            }
            em.getTransaction().commit();
            em.close();
            System.out.println("EJB student je sacuvan");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveStudent(Student s) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student studDb = em.find(Student.class, s.getJmbg());
            if (studDb == null) {
                em.persist(s);
                em.getTransaction().commit();
                em.close();
                System.out.println("EJB student je sacuvan");
                return true;
            } else {
                throw new Exception("Student vec postoji u bazi");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStudent(Student s) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student stud = em.find(Student.class, s.getJmbg());
            if (stud != null) {
                em.remove(stud);
                em.getTransaction().commit();
                em.close();
                return true;
            } else {
                throw new Exception("Student ne postoji u bazi ne postoji u bazi");
            }

        } catch (Exception ex) {
            em.close();
            ex.printStackTrace();
            return false;
        }
    }
}
