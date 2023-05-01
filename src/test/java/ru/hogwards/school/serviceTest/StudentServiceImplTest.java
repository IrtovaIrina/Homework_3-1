package ru.hogwards.school.serviceTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.repository.StudentRepository;
import ru.hogwards.school.service.StudentService;
import ru.hogwards.school.service.StudentServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StudentServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class StudentServiceImplTest {
    @Autowired
    private StudentService service;
    @MockBean
    private StudentRepository repository;
    Student q = new Student("Ron", 15);
    Student q2 = new Student("Harry", 14);
    private Collection<Student> students = new ArrayList<>();

    @Test
    public void add_success() {
        when(repository.save(q)).thenReturn(q);
        Assertions.assertEquals(service.add("Ron", 15), q);
    }

    @Test
    public void find_success() {
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(q));
        Assertions.assertEquals(service.remove(1L), q);
    }
    @Test
    public void find_withRuntimeException(){
        when(repository.findById(1L)).thenReturn(null);
        assertThrows(RuntimeException.class,
                () -> { service.find(1L);
                });
    }

    @Test
    public void remove_success() {
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(q));
        Assertions.assertEquals(service.remove(1L), q);
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
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(q));
        Assertions.assertEquals(service.update(1L,"Harry", 14 ), q2);
    }
    @Test
    public void update_withRuntimeException(){
        when(repository.findById(1L)).thenReturn(null);
        assertThrows(RuntimeException.class,
                () -> { service.update(1L,"Harry", 14 );
                });
    }

    @Test
    public void getAll_success() {
        students.add(q);
        students.add(q2);
        when(repository.findAll()).thenReturn((List<Student>) students);
        Assertions.assertEquals(service.getAll(), students);
    }
}
