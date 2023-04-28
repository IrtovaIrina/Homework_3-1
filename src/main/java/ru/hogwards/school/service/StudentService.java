package ru.hogwards.school.service;

import ru.hogwards.school.model.Student;

import java.util.Collection;
import java.util.HashMap;

public interface StudentService {
    Student add(String name, int age);
    Student remove(Long id);
    Student find(Long id);
    Student update(Long id, String name,int age);
    Collection<Student> getAll();
    Collection<Student> getAllByAge(int min, int max);
    Collection<Student> findByFaculty_id(Long faculty_id);

}
