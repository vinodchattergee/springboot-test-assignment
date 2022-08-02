package net.vinlabs.springboot.assignement.service;

import net.vinlabs.springboot.assignement.model.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    List<Student> getAllStudents();
}
