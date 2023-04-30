package ru.hogwards.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwards.school.model.Faculty;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;
    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public Faculty getFaculty(@PathVariable Long id) {
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
    @GetMapping
    public Collection<Faculty> getAllByNameAndColor(@RequestParam(required = false) String name
            , @RequestParam(required = false) String color) {
        if (!name.isEmpty() && !color.isEmpty()) {
            return facultyService.getAllByNameAndColor(name, color);
        }else {
            return facultyService.getAll();
        }
    }
    @GetMapping("{students_id}")
    public ResponseEntity<Faculty> findByStudents_id(@RequestParam("students_id")Long students_id){
        return ResponseEntity.ok(facultyService.findByStudents_id(students_id));
    }

}