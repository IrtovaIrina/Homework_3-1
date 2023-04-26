package ru.hogwards.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwards.school.model.Faculty;
import ru.hogwards.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements  FacultyService{
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    @Override
    public Faculty add(String name, String color) {
        Faculty faculty = new Faculty(name, color);
        facultyRepository.save(faculty);
        return faculty;
    }
    @Override
    public Faculty remove(Long id) {
        Optional<Faculty> facultyInOpt = facultyRepository.findById(id);
        if (facultyInOpt.isEmpty()) {
            throw new RuntimeException("Факультет c id " + id + " не найден");
        }
        Faculty faculty = facultyInOpt.get();
        facultyRepository.delete(faculty);
        return faculty;
    }

    @Override
    public Faculty find(Long id) {
        Optional<Faculty> facultyInOpt = facultyRepository.findById(id);
        if (facultyInOpt.isEmpty()) {
            throw new RuntimeException("Факультет c id " + id + " не найден");
        }
        return facultyRepository.findById(id).get();
    }
    @Override
    public Faculty update(Long id, String name,String color){
        Optional<Faculty> facultyInOpt = facultyRepository.findById(id);
        if (facultyInOpt.isEmpty()) {
            throw new RuntimeException("Факультет c id " + id + " не найден");
        }
        Faculty faculty = facultyInOpt.get();
        faculty.setName(name);
        faculty.setColor(color);
        facultyRepository.save(faculty);
        return faculty;
    }
    @Override
    public Collection<Faculty> getAll(){
        return facultyRepository.findAll();
    }
}