package ru.hogwards.school.service;

import ru.hogwards.school.model.Faculty;

import java.util.HashMap;

public interface FacultyService {
    HashMap<Long, Faculty> getFacultyMap();
    Faculty addFaculty(String name, String color);
    Faculty removeFaculty(Long id);
    Faculty findFaculty(Long id);
    Faculty updateFaculty(Long id, String name,String color);

}
