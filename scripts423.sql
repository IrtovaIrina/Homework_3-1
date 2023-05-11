SELECT student.name AS student,
       student.age,
       faculty.name AS faculty,
       student.avatar_id FROM student JOIN faculty ON student.faculty_id = faculty.id;

select student.name AS student,
       student.age FROM student WHERE student.avatar_id IS NOT NULL;
