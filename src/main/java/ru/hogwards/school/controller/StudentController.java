package ru.hogwards.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.repository.StudentRepository;
import ru.hogwards.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService, StudentRepository studentRepository){
        this.studentService = studentService;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        return ResponseEntity.ok(studentService.find(id));
    }
    @PostMapping
    public Student createStudent(@RequestParam("name") String name , @RequestParam("age") int age
            ,@RequestParam(required = false) Long facultyId){
        if (facultyId == null){
            return studentService.add(name,age);
        }
        return studentService.add(name,age,facultyId);
    }
    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestParam("name") String name, @RequestParam("age") int age){
        return ResponseEntity.ok(studentService.update(id,name,age));
    }
    @DeleteMapping("{id}")
    public  ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        return ResponseEntity.ok(studentService.remove(id));
    }
    @GetMapping
    public Collection<Student> getAllByAge(@RequestParam(required = false) int min
            , @RequestParam(required = false) int max ){
        if (min !=0 && max !=0) {
            return studentService.getAllByAge(min, max);
        }else{
            return studentService.getAll();
        }
    }
    @GetMapping("{students_id}")
    public ResponseEntity<Collection<Student>> findByFaculty_id(@RequestParam("students_id")Long students_id){
        return ResponseEntity.ok(studentService.findByFaculty_id(students_id));
    }
}