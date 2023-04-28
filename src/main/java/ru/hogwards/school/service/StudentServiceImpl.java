package ru.hogwards.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwards.school.model.Student;
import ru.hogwards.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public Student add(String name, int age) {
        Student student = new Student(name, age);
        studentRepository.save(student);
        return student;
    }
    @Override
    public Student remove(Long id) {
        Optional<Student> studentInOpt = studentRepository.findById(id);
        if (studentInOpt.isEmpty()) {
            throw new RuntimeException("Студент c id " + id + " не найден");
        }
        Student student = studentInOpt.get();
        studentRepository.delete(student);
        return student;
    }

    @Override
    public Student find(Long id) {
        Optional<Student> studentInOpt = studentRepository.findById(id);
        if (studentInOpt.isEmpty()) {
            throw new RuntimeException("Студент c id " + id + " не найден");
        }
        return studentRepository.findById(id).get();
    }
    @Override
    public Student update(Long id, String name,int age){
        Optional<Student> studentInOpt = studentRepository.findById(id);
        if (studentInOpt.isEmpty()) {
            throw new RuntimeException("Студент c id " + id + " не найден");
        }
        Student student = studentInOpt.get();
        student.setName(name);
        student.setAge(age);
        studentRepository.save(student);
        return student;
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
}
