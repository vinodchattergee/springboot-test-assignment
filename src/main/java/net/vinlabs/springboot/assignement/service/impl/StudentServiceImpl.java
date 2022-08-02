package net.vinlabs.springboot.assignement.service.impl;

import net.vinlabs.springboot.assignement.model.Student;
import org.springframework.stereotype.Service;
import net.vinlabs.springboot.assignement.repository.StudentRepository;
import net.vinlabs.springboot.assignement.service.StudentService;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
       return studentRepository.findAll();
    }
}
