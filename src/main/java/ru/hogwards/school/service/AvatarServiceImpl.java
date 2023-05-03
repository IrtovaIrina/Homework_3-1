package ru.hogwards.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwards.school.model.Avatar;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.repository.AvatarRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService{
    private final AvatarRepository repository;
    private final StudentService studentService;
    @Value("${student.avatar.dir.path}")
    private String avatarDir;

    public AvatarServiceImpl(AvatarRepository repository, StudentService studentService) {
        this.repository = repository;
        this.studentService = studentService;
    }
    @Override
    public Avatar upload(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentService.find(studentId);
        Path filePath = Path.of(avatarDir, student + "." + getExtension(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = new Avatar();
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        repository.save(avatar);
        return avatar;
    }
    @Override
    public Avatar findAvatar(Long studentId) {
        return repository.findByStudent_id(studentId);
    }
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
    public Collection<Avatar> getAllAvatars(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest).getContent();
    }
}
