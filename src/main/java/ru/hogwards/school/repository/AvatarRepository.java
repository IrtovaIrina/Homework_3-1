package ru.hogwards.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwards.school.model.Avatar;

@Repository
public interface AvatarRepository  extends JpaRepository<Avatar, Long> {
     Avatar findByStudent_id(Long student_id);
}

