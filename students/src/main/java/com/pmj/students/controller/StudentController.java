package com.pmj.students.controller;


import com.pmj.students.model.Student;
import com.pmj.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @GetMapping("/allStudents")
    public List<Student> allStudents(){

        return studentService.findAllStudents();
    }

    @PostMapping("/addStudent")
    public String addStudents(@RequestBody Student studentData){
        return studentService.saveStudent(studentData);

    }

}
