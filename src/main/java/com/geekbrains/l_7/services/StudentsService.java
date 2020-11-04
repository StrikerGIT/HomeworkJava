package com.geekbrains.l_7.services;

import com.geekbrains.l_7.entities.Student;
import com.geekbrains.l_7.exceptions.ProductNotFoundException;
import com.geekbrains.l_7.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsService {
    private StudentsRepository studentsRepository;

    @Autowired
    public void setStudentsRepository(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public Student saveOrUpdate(Student student) {
        return studentsRepository.save(student);
    }

    public void deleteById(Long id){
        studentsRepository.deleteById(id);
    }

    public Student findById(Long id) {
        return studentsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Can't found student with id = " + id));
    }

    public List<Student> findAll() {
        return studentsRepository.findAll();
    }

    public Page<Student> findAll(Specification<Student> spec, Integer page) {
        if (page < 1L) {
            page = 1;
        }
        return studentsRepository.findAll(spec, PageRequest.of(page - 1, 4));
    }
}
