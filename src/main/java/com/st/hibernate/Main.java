package com.st.hibernate;

import com.st.hibernate.dao.StudentDAO;
import com.st.hibernate.entity.Student;
import com.st.hibernate.service.SFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();

        // INSERT

        Student student1 = new Student("Alex", (short) 5);
        Student student2 = new Student("Ivan", (short) 2);
        Student student3 = new Student("Jhon", (short) 4);
        studentDAO.createStudent(student1);
        studentDAO.createStudent(student2);
        studentDAO.createStudent(student3);

        for (int i = 4; i <= 1000; i++) {
            Student studentX = new Student("Student" + i, (short) (Math.random() * 5));
            studentDAO.createStudent(studentX);
        }

        //SELECT

        // Список студентов с оценками
        List<Student> students = studentDAO.findAllStudents();
        students.forEach(System.out::println);

        //DELETE
        for (Student s : students)
        {studentDAO.deleteStudent(s);}

        //Close connection
        SFactory.closeFactory();
    }
}
