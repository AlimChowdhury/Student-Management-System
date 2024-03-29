package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService
{
    private final StudentRepository studentRepository;


    @Autowired
    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents()
    {
        return (List<Student>) studentRepository.findAll();
    }

    public void addNewStudent(Student student)
    {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent())
        {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long stdentId) {
        boolean exists = studentRepository.existsById(stdentId);

        if(!exists)
        {
            throw new IllegalStateException("student with id " + stdentId + "doesnot exists");
        }
        studentRepository.deleteById(stdentId);

    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email)
    {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
                "student with id " + studentId + "doesnot exists"));

        if(name!=null && name.length()>0 && !Objects.equals(student.getName(),name))
        {
            student.setName(name);
        }

        if(email!=null && email.length()>0 && !Objects.equals(student.getEmail(),email))
        {
            Optional<Student>studentOptional = studentRepository.findStudentByEmail(email);
                    if(studentOptional.isPresent())
                    {
                        throw new IllegalStateException("email taken");
                    }
                    student.setEmail(email);
        }

    }
}
