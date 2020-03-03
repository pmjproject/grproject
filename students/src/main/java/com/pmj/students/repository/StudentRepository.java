package com.pmj.students.repository;

import com.pmj.students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>  {
}
