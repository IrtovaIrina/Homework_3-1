package ru.hogwards.school.service;

import org.springframework.stereotype.Service;
import ru.hogwards.school.model.Faculty;
import ru.hogwards.school.model.Student;

import java.util.HashMap;

@Service
public class StudentServiceImpl implements StudentService{
    private HashMap<Long, Student> studentsMap;
    private Long count = 0L;
    @Override
    public HashMap<Long, Student> getStudentsMap(){
        return studentsMap;
    }
    @Override
    public Student addStudent(String name, int age) {
        count++;
        Student student = new Student(count, name, age);
        studentsMap.put(student.getId(), student);
        return student;
    }
    @Override
    public Student removeStudent(Long id) {
        Student student = studentsMap.get(id);
        if(!studentsMap.containsKey(id)){
            throw new RuntimeException("Вопрос не найден!");
        }
        studentsMap.remove(id);
        return student;
    }

    @Override
    public Student findStudent(Long id) {
        Student student = studentsMap.get(id);
        if(!studentsMap.containsKey(id)){
            throw new RuntimeException("Вопрос не найден!");
        }
        return student;
    }
    @Override
    public Student updateStudent(Long id, String name,int age){
        Student student = studentsMap.get(id);
        student.setName(name);
        student.setAge(age);
        return student;
    }
}
