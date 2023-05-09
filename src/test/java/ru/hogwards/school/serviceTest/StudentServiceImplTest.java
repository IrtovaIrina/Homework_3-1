package ru.hogwards.school.serviceTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwards.school.model.Faculty;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.repository.FacultyRepository;
import ru.hogwards.school.repository.StudentRepository;
import ru.hogwards.school.service.FacultyService;
import ru.hogwards.school.service.FacultyServiceImpl;
import ru.hogwards.school.service.StudentService;
import ru.hogwards.school.service.StudentServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StudentServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class StudentServiceImplTest {
    @Autowired
    private StudentService service;
    @MockBean
    private StudentRepository repository;
    Student student = new Student("Grifindor", 13);
    Student student2 = new Student("Sliserin", 14);
    private Collection<Student> collection = new ArrayList<>();

    @Test
    public void add_success()  {
        when(repository.save(student)).thenReturn(student);
        Assertions.assertEquals(service.add("Grifindor", 13), student);
    }

    @Test
    public void find_success() throws Exception {
        when(repository.findById(any(Long.class))).thenReturn(Optional.ofNullable(student));
        Assertions.assertEquals(service.find(1L), student);
    }
    @Test
    public void find_withRuntimeException(){
        when(repository.findById(any(Long.class))).thenReturn(null);
        assertThrows(RuntimeException.class,
                () -> { service.find(1L);
                });
    }

    @Test
    public void remove_success() {
        when(repository.findById(any(Long.class))).thenReturn(Optional.ofNullable(student));
        Assertions.assertEquals(service.remove(1L), student);
    }
    @Test
    public void remove_withRuntimeException(){
        when(repository.findById(1L)).thenReturn(null);
        assertThrows(RuntimeException.class,
                () -> { service.remove(1L);
                });
    }
    @Test
    public void update_success() {
        when(repository.findById(any(Long.class))).thenReturn(Optional.ofNullable(student));
        Assertions.assertEquals(service.update(1L,"Sliserin", 14 ), student2);
    }
    @Test
    public void update_withRuntimeException(){
        when(repository.findById(1L)).thenReturn(null);
        assertThrows(RuntimeException.class,
                () -> { service.update(1L,"Grifindor", 13 );
                });
    }

    @Test
    public void getAll_success() {
        collection.add(student);
        collection.add(student2);
        when(repository.findAll()).thenReturn((List<Student>) collection);
        Assertions.assertEquals(service.getAll(), collection);
    }

}