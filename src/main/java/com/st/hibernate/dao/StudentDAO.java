package com.st.hibernate.dao;

import com.st.hibernate.entity.Student;
import com.st.hibernate.service.SFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class StudentDAO {

    private EntityManager em;

    public StudentDAO() {
        EntityManagerFactory emFactory = SFactory.getFactory();
        this.em = emFactory.createEntityManager();
    }

    public void createStudent(Student student) {
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
    }

    public void updateStudent(Student student) {
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
    }

    public void deleteStudent(Student student) {
        em.getTransaction().begin();
        em.remove(student);
        em.getTransaction().commit();
    }

    public Student findStudent(Long id) {
        return em.find(Student.class, id);
    }

    public List<Student> findAllStudents() {
        return em.createQuery("from Student",Student.class).getResultList();
    }
}
