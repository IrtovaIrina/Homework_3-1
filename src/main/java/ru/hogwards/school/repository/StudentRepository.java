package ru.hogwards.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwards.school.model.Student;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);
    Collection<Student> findStudentsByFaculty_id(Long faculty_id);
    @Query(value = "SELECT COUNT(name) FROM student", nativeQuery = true)
    int countOfStudents();
    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    float averageAge ();
    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    Collection<Student> lastFiveStudents();

}
