package ru.hogwards.school.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hogwards.school.controller.FacultyController;
import ru.hogwards.school.model.Faculty;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.repository.FacultyRepository;
import ru.hogwards.school.service.FacultyService;
import ru.hogwards.school.service.FacultyServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
public class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private FacultyRepository repository;
    @SpyBean
    private FacultyServiceImpl service;
    @InjectMocks
    private FacultyControllerTest controller;

    final Faculty faculty = new Faculty(1L,"1234","123");
    final Faculty faculty2 = new Faculty(1L,"12345","1234");

    @Test
    void getFaculty_success() throws Exception {

        when(service.find(1L)).thenReturn(faculty);

        //Начало теста
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/{id}",1L)
                        .content(objectMapper.writeValueAsString(faculty))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void createFaculty_success() throws Exception {


        //Подготовка ожидаемого результата
        when(service.add("1234","123")).thenReturn(faculty);

        //Начало теста
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(objectMapper.writeValueAsString(faculty))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void updateFaculty_success() throws Exception {
        when(service.update(1L,"12345","1234")).thenReturn(faculty2);

        //Начало теста
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/{id}?name=12345&color=1234",1L)
                        .content(objectMapper.writeValueAsString(faculty2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void deleteFaculty_success() throws Exception {
        when(service.remove(1L)).thenReturn(faculty);

        //Начало теста
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/{id}",1L)
                        .content(objectMapper.writeValueAsString(faculty))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllByNameAndColor_success() throws Exception {
        when(repository.findFacultiesByNameIgnoreCaseAndColorIgnoreCase(any(String.class),any(String.class))).thenReturn(List.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/{id}?name=1234&color=123",1L)
                        .content(objectMapper.writeValueAsString(faculty))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findByStudents_id_success() throws Exception {
        when(repository.findFacultyByStudents_id(any(Long.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/{student_id}",any(Long.class))
                        .content(objectMapper.writeValueAsString(faculty))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}