package ru.hogwards.school.service;

import ru.hogwards.school.model.Faculty;
import ru.hogwards.school.model.Student;

import java.util.Collection;
import java.util.HashMap;

public interface FacultyService {
    Faculty add(String name, String color);
    Faculty remove(Long id);
    Faculty find(Long id);
    Faculty update(Long id, String name,String color);
    Collection<Faculty> getAll();

}
