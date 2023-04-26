package ru.hogwards.school.service;

import ru.hogwards.school.model.Student;

import java.util.HashMap;

public interface StudentService {
    HashMap<Long, Student> getStudentsMap();
    Student addStudent(String name, int age);
    Student removeStudent(Long id);
    Student findStudent(Long id);
    Student updateStudent(Long id, String name,int age);
}
