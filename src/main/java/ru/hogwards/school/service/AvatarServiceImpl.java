package ru.hogwards.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwards.school.model.Avatar;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.repository.AvatarRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Optional;

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
    public void deleteAvatarById(Long id) {
        Optional<Avatar> avatar = repository.findById(id);
        try {
            repository.deleteById(id);
            Files.deleteIfExists(Path.of(avatar.get().getFilePath()));
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException();
        }
    }
    @Override
    public void upload(Long studentId, MultipartFile file) throws IOException {
        Student student = studentService.find(studentId);
        Path filePath = Path.of(avatarDir,
                studentId + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, StandardOpenOption.CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }

        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(generateImagePhoto(filePath));
        repository.save(avatar);
    }
    private byte[] generateImagePhoto(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);
            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }

    }
    @Override
    public Avatar findAvatar(Long studentId) {
        return repository.findByStudent_id(studentId);
    }
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
    @Override
    public Collection<Avatar> getAllAvatars() {
        return repository.findAll();
    }
}
