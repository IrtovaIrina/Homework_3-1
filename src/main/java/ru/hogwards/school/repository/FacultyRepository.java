package ru.hogwards.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwards.school.model.Faculty;
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
