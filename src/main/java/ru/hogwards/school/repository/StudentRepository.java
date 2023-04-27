package ru.hogwards.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwards.school.model.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
