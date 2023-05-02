package ru.hogwards.school.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwards.school.controller.FacultyController;
import ru.hogwards.school.controller.StudentController;
import ru.hogwards.school.model.Faculty;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.service.FacultyServiceImpl;
import ru.hogwards.school.service.StudentServiceImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private StudentServiceImpl service;

    final Student student = new Student(1L,"1234",123);
    final Student student2 = new Student(1L,"12345",1234);

    @Test
    void getStudent_success() throws Exception {

        when(service.find(any(Long.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/{id}",1L)
                        .content(objectMapper.writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void createStudent_success() throws Exception {


        //Подготовка ожидаемого результата
        when(service.add(any(String.class),any(int.class))).thenReturn(student);

        //Начало теста
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student?name=1234&age=123")
                        .content(objectMapper.writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void updateStudent_success() throws Exception {
        when(service.update(any(Long.class),any(String.class),any(int.class))).thenReturn(student2);

        //Начало теста
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student/{id}?name=12345&age=1234",1L)
                        .content(objectMapper.writeValueAsString(student2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void deleteStudent_success() throws Exception {
        when(service.remove(any(Long.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/{id}",1L)
                        .content(objectMapper.writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllByAge_success() throws Exception {
        when(service.getAllByAge(any(int.class),any(int.class))).thenReturn(List.of(student));
        when(service.getAll()).thenReturn(List.of(student));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student?min=1234&max=123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findByFaculty_id_success() throws Exception {
        when(service.findByFaculty_id(any(Long.class))).thenReturn(List.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/faculty_id/{faculty_id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}