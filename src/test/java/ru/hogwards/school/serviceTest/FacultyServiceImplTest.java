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
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {FacultyServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class FacultyServiceImplTest {
    @Autowired
    private FacultyService service;
    @MockBean
    private FacultyRepository repository;
    Faculty q = new Faculty("Grifindor", "Red");
    Faculty q2 = new Faculty("Sliserin", "Blue");
    private Collection<Faculty> faculties = new ArrayList<>();

    @Test
    public void add_success() {
        when(repository.save(q)).thenReturn(q);
        Assertions.assertEquals(service.add("Grifindor", "Red"), q);
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
        Assertions.assertEquals(service.update(1L,"Sliserin", "Blue" ), q2);
    }
    @Test
    public void update_withRuntimeException(){
        when(repository.findById(1L)).thenReturn(null);
        assertThrows(RuntimeException.class,
                () -> { service.update(1L,"Grifindor", "Red" );
                });
    }

    @Test
    public void getAll_success() {
        faculties.add(q);
        faculties.add(q2);
        when(repository.findAll()).thenReturn((List<Faculty>) faculties);
        Assertions.assertEquals(service.getAll(), faculties);
    }

}
