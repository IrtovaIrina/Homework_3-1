//1
select * from student
where student.age >= 10 and student.age <= 20;
//2
select student.name from student;
//3
select * from student
where name like '%о%';
//4
select * from student
where student.age < student.id;
//5
select * from student
order by student.age;