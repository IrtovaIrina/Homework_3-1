package ru.hogwards.school.controllerTest;

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
    @MockBean
    private FacultyRepository repository;
    @SpyBean
    private FacultyService service;
    @InjectMocks
    private FacultyControllerTest controller;

    JSONObject facultyObject = new JSONObject();
    final Faculty faculty = new Faculty(1L,"1234","123");

    @Test
    void getFaculty_success() throws Exception {
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + faculty.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(faculty.getId()))
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));

        verify(repository, times(1)).findById(any(Long.class));
    }
    @Test
    void createFaculty_success() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", faculty.getId());
        jsonObject.put("name", faculty.getName());
        jsonObject.put("color", faculty.getColor());

        when(repository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(faculty.getId()))
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));

        verify(repository, times(1)).save(any(Faculty.class));
    }
    @Test
    void updateFaculty_success() throws Exception {
        Faculty newFaculty = new Faculty( "Грифиндор", "Красный");
        newFaculty.setId(1L);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", newFaculty.getId());
        jsonObject.put("name", newFaculty.getName());
        jsonObject.put("color", newFaculty.getColor());
        when(repository.save(any(Faculty.class))).thenReturn(newFaculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(newFaculty.getId()))
                .andExpect((jsonPath("$.name").value(newFaculty.getName())))
                .andExpect((jsonPath("$.color").value(newFaculty.getColor())));

        verify(repository, times(1)).save(any(Faculty.class));
    }
    @Test
    void deleteFaculty_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + faculty.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(repository, times(1)).deleteById(faculty.getId());
    }

    @Test
    void getAllByNameAndColor_success() throws Exception {
        when(repository.findFacultiesByNameIgnoreCaseAndColorIgnoreCase(any(String.class),any(String.class))).thenReturn(List.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty?name=gryffindor&color=Red")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo(faculty.getName())))
                .andExpect(jsonPath("$[0].color", equalTo(faculty.getColor())));

        verify(repository, times(1))
                .findFacultiesByNameIgnoreCaseAndColorIgnoreCase(any(String.class),any(String.class));
    }

    @Test
    void findByStudents_id_success() throws Exception {
        Student harry = new Student( "Harry", 11);
        harry.setId(1L);
        faculty.setStudents(List.of(harry));

        when(repository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/students?id=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo(harry.getName())))
                .andExpect(jsonPath("$[0].age", equalTo(harry.getAge())));

        verify(repository, times(1)).findById(any(Long.class));
    }
}