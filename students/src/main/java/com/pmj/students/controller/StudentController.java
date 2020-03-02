package com.pmj.students.controller;


import com.pmj.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @GetMapping("/allStudents")
    public String allStudents(){

        return studentService.findAllStudents();
    }

}
