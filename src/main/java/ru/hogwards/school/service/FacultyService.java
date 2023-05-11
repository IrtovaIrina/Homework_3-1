package ru.hogwards.school.service;

import ru.hogwards.school.model.Faculty;

import java.util.Collection;
import java.util.Optional;

public interface FacultyService {
    Faculty add(String name, String color);
    Faculty remove(Long id);
    Faculty find(Long id) throws Exception;
    Faculty update(Long id, String name,String color);
    Collection<Faculty> getAll();
    Collection<Faculty> getAllByNameAndColor(String name, String color);
    Faculty findByStudents_id(Long students_id);
    String mostLongName();
}