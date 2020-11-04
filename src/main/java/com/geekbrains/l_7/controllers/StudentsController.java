package com.geekbrains.l_7.controllers;

import com.geekbrains.l_7.entities.Student;
import com.geekbrains.l_7.repositories.StudentsRepository;
import com.geekbrains.l_7.services.StudentsService;
import com.geekbrains.l_7.utils.StudentFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/students")
public class StudentsController {
    private StudentsService studentsService;

    @Autowired
    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping
    public String showAll(Model model, @RequestParam Map<String, String> requestParams) {
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("p", "1"));
        StudentFilter studentFilter = new StudentFilter(requestParams);
        Page<Student> students = studentsService.findAll(studentFilter.getSpec(), pageNumber);
        model.addAttribute("students", students);
        model.addAttribute("filterDef", studentFilter.getFilterDefinition().toString());
        return "all_students";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_student_form";
    }

    @PostMapping("/add")
    public String saveNewStudent(@ModelAttribute Student student) {
        studentsService.saveOrUpdate(student);
        return "redirect:/students/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudentGet(@PathVariable Long id, Model model) {
        studentsService.deleteById(id);
        return "redirect:/students/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentsService.findById(id));
        return "edit_student_form";
    }

    @PostMapping("/edit")
    public String modifyStudent(@ModelAttribute Student student) {
        studentsService.saveOrUpdate(student);
        return "redirect:/students/";
    }
}