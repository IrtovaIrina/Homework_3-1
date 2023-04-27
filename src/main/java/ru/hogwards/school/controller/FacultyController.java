package ru.hogwards.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hogwards.school.model.Faculty;
import ru.hogwards.school.service.FacultyService;

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
}

