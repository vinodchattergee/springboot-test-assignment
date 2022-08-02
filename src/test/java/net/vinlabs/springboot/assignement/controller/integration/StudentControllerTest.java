package net.vinlabs.springboot.assignement.controller.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.vinlabs.springboot.assignement.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import net.vinlabs.springboot.assignement.repository.StudentRepository;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentControllerTest extends AbstractTestContainerBase {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();
    }

    @Test
    public void givenStudentObject_whenCreateStudent_thenReturnSavedStudent() throws Exception {
        //Given
        Student student = Student.builder()
                .firstName("Vinod")
                .lastName("Chattergee")
                .email("Vinod@gmail.com")
                .build();

        //When
        ResultActions response = mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));


        //Then
        //MockMvcResultHandlers.print() - Prints the request and the response.
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(student.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(student.getLastName())))
                .andExpect(jsonPath("$.email", is(student.getEmail())));

    }

    @Test
    public void givenListOfStudents_whenGetAllStudents_thenReturnListOfStudents() throws Exception {
        //Given

        List<Student> students = List.of(
                Student.builder()
                        .firstName("Vinod")
                        .lastName("Chattergee")
                        .email("Vinod@gmail.com")
                        .build(),
                Student.builder()
                        .firstName("Gautham")
                        .lastName("Vinod")
                        .email("Gautham@gmail.com")
                        .build()
        );

        studentRepository.saveAll(students);

        //When
        ResultActions response = mockMvc.perform(get("/api/students")
                .contentType(MediaType.APPLICATION_JSON));

        //Then
        //MockMvcResultHandlers.print() - Prints the request and the response.
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.size()", is(students.size())))
                .andExpect(status().is2xxSuccessful());

    }
}
