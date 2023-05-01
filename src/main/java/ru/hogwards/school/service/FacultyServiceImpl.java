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
    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    @Override
    public Faculty add(String name, String color) {
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setColor(color);
        facultyRepository.save(faculty);
        return faculty;
    }
    @Override
    public Faculty remove(Long id) {
        //if (facultyRepository.findId(id) == 0L)
            //throw new RuntimeException();
        //Faculty faculty = facultyRepository.getById(id);
        //facultyRepository.delete(faculty);
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
    @Override
    public Collection<Faculty> getAllByNameAndColor(String name, String color){
        return facultyRepository.findFacultiesByNameIgnoreCaseAndColorIgnoreCase(name,color);
    }
    @Override
    public Faculty findByStudents_id(Long students_id){
        return facultyRepository.findFacultyByStudents_id(students_id);
    }
}
