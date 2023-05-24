package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository)
    {
        return args ->{
           Student joy= new Student(
                    "Joy",
                    "joy@gmail.com",
                    LocalDate.of(2000, Month.AUGUST,22)
            );

            Student tasni= new Student(
                    "Tasni",
                    "tas@gmail.com",
                    LocalDate.of(2016, Month.AUGUST,6)
            );

            repository.saveAll(List.of(joy,tasni)
            );
        };
    }
}
