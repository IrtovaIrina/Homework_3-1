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
        return facultyRepository.save(faculty);
    }
    @Override
    public Faculty remove(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()){
            facultyRepository.delete(faculty.get());
            return faculty.get();
        }
        throw new RuntimeException("Факультет c id " + id + " не найден");
    }

    @Override
    public Faculty find(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()){
            return faculty.get();
        }
        throw new RuntimeException("Факультет c id " + id + " не найден");
    }
    public Faculty update(Long id, String name, String color) {
        Optional<Faculty> facultyForUpdateOpt = facultyRepository.findById(id);
        if (facultyForUpdateOpt.isEmpty()) {
            throw new RuntimeException("Факультет c id " + id + " не найден");
        }
        Faculty facultyForUpdate = facultyForUpdateOpt.get();
        facultyForUpdate.setName(name);
        facultyForUpdate.setColor(color);

        facultyRepository.save(facultyForUpdate);
        return facultyForUpdate;
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
        Optional<Faculty> faculty = Optional.ofNullable(facultyRepository.findFacultyByStudents_id(students_id));
        if (faculty.isPresent()){
            return faculty.get();
        }
        throw new RuntimeException(("Факультет c students_id " + students_id + " не найден"));
    }
}
