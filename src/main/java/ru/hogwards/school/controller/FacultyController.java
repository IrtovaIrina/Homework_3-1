package ru.hogwards.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwards.school.model.Faculty;
import ru.hogwards.school.repository.FacultyRepository;
import ru.hogwards.school.service.FacultyService;
import ru.hogwards.school.service.FacultyServiceImpl;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyServiceImpl facultyService;
    private final FacultyRepository facultyRepository;
    @Autowired
    public FacultyController(FacultyServiceImpl facultyService, FacultyRepository facultyRepository) {
        this.facultyService = facultyService;
        this.facultyRepository = facultyRepository;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @GetMapping("{id}")
    public Faculty getFaculty(@PathVariable Long id) throws Exception {
        return facultyService.find(id);
    }

    @PostMapping
    public Faculty createFaculty(@RequestParam("name") String name, @RequestParam("color") String color) {
        return facultyService.add(name, color);
    }

    @PutMapping("{id}")
    public Faculty updateFaculty(@PathVariable Long id, @RequestParam("name") String name, @RequestParam("color") String color) {
        return facultyService.update(id, name, color);
    }
    @DeleteMapping("{id}")
    public Faculty deleteFaculty(@PathVariable Long id) {
        return facultyService.remove(id);
    }
    @GetMapping//ok
    public Collection<Faculty> getAllByNameAndColor(@RequestParam(required = false) String name
            , @RequestParam(required = false) String color) {
        if (name.isBlank() && color.isBlank()) {
            return facultyService.getAll();
        }else {
            return facultyService.getAllByNameAndColor(name, color);
        }
    }
    @GetMapping("{students_id}")
    public ResponseEntity<Faculty> findByStudents_id(@RequestParam("students_id")Long students_id){
        return ResponseEntity.ok(facultyService.findByStudents_id(students_id));
    }

}