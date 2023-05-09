package ru.hogwards.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);
    @Override
    public Student add(String name, int age, Long facultyId) {
        logger.info("Был вызван метод createStudent");
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
        logger.info("Был вызван метод createStudent");
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        studentRepository.save(student);
        return student;
    }
    @Override
    public Student remove(Long id) {
        logger.info("Был вызван метод deleteStudent");
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
            return student.get();
        }
        throw new RuntimeException("Студент c id " + id + " не найден");
    }

    @Override
    public Student find(Long id) {
        logger.info("Был вызван метод uploadAvatar");
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        }
        throw new RuntimeException("Студент c id " + id + " не найден");
    }
    @Override
    public Student update(Long id, String name,int age){
        logger.info("Был вызван метод getStudent");
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
        logger.info("Был вызван метод getAll");
        return studentRepository.findAll();
    }
    @Override
    public Collection<Student> getAllByAge(int min, int max){
        logger.info("Был вызван метод getAll");
        return studentRepository.findByAgeBetween(min, max);
    }
    @Override
    public Collection<Student> findByFaculty_id(Long faculty_id){
        logger.info("Был вызван метод findByFaculty_id");
        return studentRepository.findStudentsByFaculty_id(faculty_id);
    }
    @Override
    public int countOfStudents(){
        logger.info("Был вызван метод studentsCount");
        return studentRepository.countOfStudents();
    }
    @Override
    public float averageAge(){
        logger.info("Был вызван метод averageAge");
        return studentRepository.averageAge();
    }
    @Override
    public Collection<Student> lastFiveStudents(){
        logger.info("Был вызван метод lastFiveStudents");
        return studentRepository.lastFiveStudents();
    }
}
