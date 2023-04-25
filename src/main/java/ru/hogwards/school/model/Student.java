package ru.hogwards.school.model;

public class Student {
    private Long id;
    private String name;
    private int age;
    private static Long studentsCount = -1L;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        studentsCount++;
        id = studentsCount;
    }
}
