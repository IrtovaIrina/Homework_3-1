package ru.hogwards.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwards.school.model.Avatar;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

public interface AvatarService {
    void upload(Long studentId, MultipartFile file) throws IOException;
    Avatar findAvatar(Long bookId);
    void deleteAvatarById(Long id);
    Collection<Avatar> getAllAvatars();
}
