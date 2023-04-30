package ru.hogwards.school.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwards.school.model.Avatar;
import ru.hogwards.school.service.AvatarService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
@RestController
@RequestMapping("/student")
public class AvatarController {
    private final AvatarService avatarService;
    @Autowired
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id,
                                               @RequestParam MultipartFile avatar) {
        try {
            avatarService.upload(id, avatar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}/avatar")
    public ResponseEntity<Void> deleteAvatarById(@PathVariable Long id) {
        avatarService.deleteAvatarById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/avatar/data")
    public ResponseEntity<byte[]> downloadAvatarFromData(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }
    @GetMapping(value = "/{id}/avatar/disk")
    public ResponseEntity<byte[]> downloadAvatarFromDisk(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatar(id);
        Path path = Path.of(avatar.getFilePath());
        byte[] media;
        try {
            media = Files.readAllBytes(path);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentLength(media.length)
                    .contentType(MediaType.parseMediaType(avatar.getMediaType()))
                    .body(media);
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentLength(e.getMessage().length())
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(e.getMessage().getBytes(StandardCharsets.UTF_8));
        }
    }
    @GetMapping(value = "/avatars")
    public Collection<Avatar> getAllAvatars() {
        return avatarService.getAllAvatars();
    }
}
