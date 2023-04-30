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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AvatarServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class AvatarServiceImplTest {
    @Autowired
    private AvatarService service;
    @MockBean
    private AvatarRepository repository;
    @MockBean
    private AvatarService avatarService;
    //void upload(Long studentId, MultipartFile file) throws IOException;
    //Avatar findAvatar(Long bookId);
    //void deleteAvatarById(Long id);
    //Collection<Avatar> getAllAvatars();
    Student student = new Student("Ron", 15);
    Avatar avatar= new Avatar(1L,"1",1L,"1",new byte[]{1},student);
    @Test
    public void findAvatar_success(){
        when(repository.findByStudent_id(2L)).thenReturn(avatar);
        Assertions.assertEquals(null,service.findAvatar(2L));
    }

}

