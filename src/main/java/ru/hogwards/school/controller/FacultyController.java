package ru.hogwards.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.hogwards.school.model.Faculty;
import ru.hogwards.school.service.FacultyServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyServiceImpl facultyService;
    @Autowired
    public FacultyController(FacultyServiceImpl facultyService) {
        this.facultyService = facultyService;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @GetMapping("{id}")//
    public Faculty getFaculty(@PathVariable("id") Long id) {
        return facultyService.find(id);
    }
    @PostMapping
    public Faculty createFaculty(@RequestParam("name") String name, @RequestParam("color") String color) {
        return facultyService.add(name, color);
    }

    @PutMapping("{id}")
    public Faculty updateFaculty(@PathVariable("id") Long id, @RequestParam("name") String name, @RequestParam("color") String color) {
        return facultyService.update(id, name, color);
    }
    @DeleteMapping("{id}")
    public Faculty deleteFaculty(@PathVariable Long id)  {
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
    @GetMapping("student_id/{student_id}")
    public Faculty findByStudents_id(@PathVariable("student_id") Long students_id){
        return facultyService.findByStudents_id(students_id);
    }

}