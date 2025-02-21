package com.manage.student.controller;

import com.manage.student.entity.Student;
import com.manage.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //handler method to handle list students and return mode and view
    @GetMapping("/students")
    public String listStudents(Model model){
        model.addAttribute("students",studentService.getAllStudents());
        return "students";

    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model){
        //create student object to hold student form data
        Student student=new Student();
        //key used in view //actual object u want to add to model
        model.addAttribute("student",student);
        return "create_student";
    }

    @PostMapping("/students")
    //@ModelAttribute("student") is used to bind the form data from the view (e.g., a form submitted by the user) to a model object (Student) in the controller method.
    public String saveStudent(@ModelAttribute("student") Student student){
    studentService.saveStudent(student);
    return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model){
    model.addAttribute("student",studentService.getStudentById(id));
    return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model) {
        //get student from db by id
        Student existingStudenet = studentService.getStudentById(id);
        existingStudenet.setId(id);
        existingStudenet.setFirstName(student.getFirstName());
        existingStudenet.setLastName(student.getLastName());
        existingStudenet.setEmail(student.getEmail());

        //save updated student object
        studentService.updateStudent(existingStudenet);
        return "redirect:/students";

    }
    //delete student
    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return "redirect:/students";


    }
}
