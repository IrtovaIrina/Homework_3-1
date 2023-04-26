package ru.hogwards.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        return ResponseEntity.ok(studentService.findStudent(id));
    }
    @PostMapping
    public Student createStudent(@RequestParam("name") String name , @RequestParam("age") int age ){
        return studentService.addStudent(name,age);
    }
    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestParam("name") String name, @RequestParam("age") int age){
        return ResponseEntity.ok(studentService.updateStudent(id,name,age));
    }
    @DeleteMapping("{id}")
    public  ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        return ResponseEntity.ok(studentService.removeStudent(id));
    }
}
