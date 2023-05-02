package ru.hogwards.school.serviceTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwards.school.model.Faculty;
import ru.hogwards.school.repository.FacultyRepository;
import ru.hogwards.school.service.FacultyService;
import ru.hogwards.school.service.FacultyServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {FacultyServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class FacultyServiceImplTest {
    @Autowired
    private FacultyService service;
    @MockBean
    private FacultyRepository repository;
    Faculty faculty = new Faculty(1L,"Grifindor", "Red");
    Faculty faculty1 = new Faculty(1L,"Sliserin", "Blue");
    private Collection<Faculty> faculties = new ArrayList<>();

    @Test
    public void add_success()  {
        when(repository.save(faculty)).thenReturn(faculty);
        Assertions.assertEquals(service.add("Grifindor", "Red"), faculty);
    }

    @Test
    public void find_success() throws Exception {
        when(repository.findById(any(Long.class))).thenReturn(Optional.ofNullable(faculty));
        Assertions.assertEquals(service.find(1L), faculty);
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
        when(repository.findById(any(Long.class))).thenReturn(Optional.ofNullable(faculty));
        Assertions.assertEquals(service.remove(1L), faculty);
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
        when(repository.findById(any(Long.class))).thenReturn(Optional.ofNullable(faculty));
        Assertions.assertEquals(service.update(1L,"Sliserin", "Blue" ), faculty1);
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
        faculties.add(faculty);
        faculties.add(faculty1);
        when(repository.findAll()).thenReturn((List<Faculty>) faculties);
        Assertions.assertEquals(service.getAll(), faculties);
    }

}
