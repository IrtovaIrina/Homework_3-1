package ru.hogwards.school.serviceTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwards.school.model.Avatar;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.repository.AvatarRepository;
import ru.hogwards.school.repository.FacultyRepository;
import ru.hogwards.school.service.AvatarService;
import ru.hogwards.school.service.AvatarServiceImpl;
import ru.hogwards.school.service.FacultyService;
import ru.hogwards.school.service.StudentService;

import java.io.IOException;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = AvatarServiceImpl.class)
@ExtendWith(SpringExtension.class)
public class AvatarServiceImplTest {
    @Autowired
    private AvatarService service;
    @MockBean
    private AvatarRepository repository;
    @MockBean
    private StudentService studentService;
    Student student = new Student("1",1)
    Avatar avatar = new Avatar("1",1L,"1",new byte[]{1},student);
    //Avatar upload(Long studentId, MultipartFile file) throws IOException;
    //Avatar findAvatar(Long bookId);
    //Collection<Avatar> getAllAvatars(int page, int size);
    MultipartFile file = new MultipartFil
    @Test
    public void upload_success() throws IOException {
        when(studentService.find(any(Long.class))).thenReturn(student);
        when(repository.save(avatar)).thenReturn(avatar);
        Assertions.assertEquals(service.upload(1L,));
    }
}
