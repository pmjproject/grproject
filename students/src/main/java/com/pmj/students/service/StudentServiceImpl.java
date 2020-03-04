package com.pmj.students.service;

import com.pmj.students.model.Student;
import com.pmj.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public String saveStudent(Student studentData) {
        studentRepository.save(studentData);
        return "Student Data Saved";
    }

    @Override
    public String updateStudent(Student newStudentData) {
        String msg = null;
        if(newStudentData.getId() != null){
            studentRepository.save(newStudentData);
            msg = "Data Saved";
            return msg;
        }
        else {
            msg = "Error";
           return msg;
        }
    }
}
