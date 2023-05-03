package ru.hogwards.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwards.school.model.Faculty;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.repository.FacultyRepository;
import ru.hogwards.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }
    @Override
    public Student add(String name, int age, Long facultyId) {

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        Optional<Faculty> faculty = facultyRepository.findById(facultyId);
        if (faculty.isPresent()){
            student.setFaculty(faculty.get());
        }
        studentRepository.save(student);
        return student;
    }
    @Override
    public Student add(String name, int age) {
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        studentRepository.save(student);
        return student;
    }
    @Override
    public Student remove(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
            return student.get();
        }
        throw new RuntimeException("Студент c id " + id + " не найден");
    }

    @Override
    public Student find(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        }
        throw new RuntimeException("Студент c id " + id + " не найден");
    }
    @Override
    public Student update(Long id, String name,int age){
        Optional<Student> studentObj = studentRepository.findById(id);
        if (studentObj.isPresent()) {
            Student student = studentObj.get();
            student.setName(name);
            student.setAge(age);
            studentRepository.save(student);
            return student;
        }
        throw new RuntimeException("Студент c id " + id + " не найден");
    }
    @Override
    public Collection<Student> getAll(){
        return studentRepository.findAll();
    }
    @Override
    public Collection<Student> getAllByAge(int min, int max){
        return studentRepository.findByAgeBetween(min, max);
    }
    @Override
    public Collection<Student> findByFaculty_id(Long faculty_id){
        return studentRepository.findStudentsByFaculty_id(faculty_id);
    }
    @Override
    public int countOfStudents(){
        return studentRepository.countOfStudents();
    }
    @Override
    public float averageAge(){
        return studentRepository.averageAge();
    }
    @Override
    public Collection<Student> lastFiveStudents(){
        return studentRepository.lastFiveStudents();
    }
}
