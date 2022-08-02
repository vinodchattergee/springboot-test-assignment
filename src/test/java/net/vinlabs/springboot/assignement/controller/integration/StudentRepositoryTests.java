package net.vinlabs.springboot.assignement.controller.integration;

import net.vinlabs.springboot.assignement.model.Student;
import net.vinlabs.springboot.assignement.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTests extends AbstractTestContainerBase {
    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    public void setup(){

        studentRepository.deleteAll();
    }

    // JUnit test for save employee operation
    //@DisplayName("JUnit test for save employee operation")
    @Test
    public void givenStudentObject_whenSave_thenReturnSavedStudent(){

        //given - precondition or setup
        Student student = Student.builder()
                .firstName("Vinod")
                .lastName("Vinod")
                .email("Vinod@gmail,com")
                .build();
        // when - action or the behaviour that we are going test
        Student savedStudent = studentRepository.save(student);

        // then - verify the output
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId()).isGreaterThan(0);
    }


    // JUnit test for get all employees operation
    @DisplayName("JUnit test for get all student operation")
    @Test
    public void givenStudentsList_whenFindAll_thenStudentsList(){
        // given - precondition or setup
        Student student1 = Student.builder()
                .firstName("Vinod")
                .lastName("Vinod")
                .email("Vinod@gmail,com")
                .build();

        Student student2 = Student.builder()
                .firstName("John")
                .lastName("Cena")
                .email("cena@gmail,com")
                .build();

        studentRepository.save(student1);
        studentRepository.save(student2);

        // when -  action or the behaviour that we are going test
        List<Student> studentsList = studentRepository.findAll();

        // then - verify the output
        assertThat(studentsList).isNotNull();
        assertThat(studentsList.size()).isEqualTo(2);

    }
}
