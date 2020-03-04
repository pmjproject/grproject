package com.pmj.students.repository;

import com.pmj.students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Override
    public <S extends Student> S save(S s);

    @Override
    public List<Student> findAll();
}
