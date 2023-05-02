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
import ru.hogwards.school.model.Faculty;
import ru.hogwards.school.service.FacultyServiceImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
public class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private FacultyServiceImpl service;

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
        when(service.update(any(long.class),any(String.class),any(String.class))).thenReturn(faculty2);

        //Начало теста
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty/{id}?name=12345&color=1234",1L)
                        .content(objectMapper.writeValueAsString(faculty2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void deleteFaculty_success() throws Exception {
        when(service.remove(any(long.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/{id}",1L)
                        .content(objectMapper.writeValueAsString(faculty))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllByNameAndColor_success() throws Exception {
        when(service.getAllByNameAndColor("1234","123")).thenReturn(List.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/{id}?name=1234&color=123",1L)
                        .content(objectMapper.writeValueAsString(faculty))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findByStudents_id_success() throws Exception {
        when(service.findByStudents_id(1L)).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/{student_id}",any(Long.class))
                        .content(objectMapper.writeValueAsString(faculty))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}