package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByName(String name);
    //SELECT *FROM student WHERE email=
    Optional<Student> findStudentByEmail(String email);

}