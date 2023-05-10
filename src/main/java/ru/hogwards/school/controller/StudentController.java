package ru.hogwards.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.repository.StudentRepository;
import ru.hogwards.school.service.StudentService;
import ru.hogwards.school.service.StudentServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentServiceImpl studentService;
    @Autowired
    public StudentController(StudentServiceImpl studentService){
        this.studentService = studentService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    @GetMapping("{id}")
    public Student getStudent(@PathVariable Long id){
        return studentService.find(id);
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
    public Student updateStudent(@PathVariable Long id, @RequestParam("name") String name, @RequestParam("age") int age){
        return studentService.update(id,name,age);
    }
    @DeleteMapping("{id}")
    public  Student deleteStudent(@PathVariable Long id){
        return studentService.remove(id);
    }
    @GetMapping
    public Collection<Student> getAll(@RequestParam(required = false) int min
            , @RequestParam(required = false) int max ){
        if (min !=0 && max !=0) {
            return studentService.getAllByAge(min, max);
        }else{
            return studentService.getAll();
        }
    }
    @GetMapping("faculty_id/{faculty_id}")
    public Collection<Student> findByFaculty_id(@PathVariable("faculty_id")Long faculty_id){
        return studentService.findByFaculty_id(faculty_id);
    }
    @GetMapping("/count")
    public int studentsCount(){
        return studentService.countOfStudents();
    }
    @GetMapping("/average-age")
    public float averageAge(){
        return studentService.averageAge();
    }
    @GetMapping("/last-five-students")
    public Collection<Student> lastFiveStudents(){
        return studentService.lastFiveStudents();
    }
    @GetMapping("/get-all/A")
    public Collection<Student> studentsWithA(){
        return studentService.studentsWithA();
    }
    @GetMapping("/average-age/stream")
    public double averageAgeWithStream(){
        return studentService.averageAgeWithStream();
    }

}