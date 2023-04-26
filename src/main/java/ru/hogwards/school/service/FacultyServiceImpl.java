package ru.hogwards.school.service;

import org.springframework.stereotype.Service;
import ru.hogwards.school.model.Faculty;
import java.util.HashMap;
@Service
public class FacultyServiceImpl implements  FacultyService{
    private HashMap<Long, Faculty> facultiesMap;
    private Long count = 0L;
    @Override
    public HashMap<Long, Faculty> getFacultyMap(){
        return facultiesMap;
    }
    @Override
    public Faculty addFaculty(String name, String color) {
        count++;
        Faculty faculty = new Faculty(count, name, color);
        facultiesMap.put(faculty.getId(), faculty);
        return faculty;
    }
    @Override
    public Faculty removeFaculty(Long id) {
        Faculty faculty = facultiesMap.get(id);
        if(!facultiesMap.containsKey(id)){
            throw new RuntimeException("Вопрос не найден!");
        }
        facultiesMap.remove(id);
        count--;
        return faculty;
    }

    @Override
    public Faculty findFaculty(Long id) {
        Faculty faculty = facultiesMap.get(id);
        if(!facultiesMap.containsKey(id)){
            throw new RuntimeException("Вопрос не найден!");
        }
        return faculty;
    }
    @Override
    public Faculty updateFaculty(Long id, String name,String color){
        Faculty faculty = facultiesMap.get(id);
        faculty.setName(name);
        faculty.setColor(color);
        return faculty;
    }
}