package ru.hogwards.school.model;

public class Faculty {
    private Long id;
    private String name;
    private String color;
    private Long facultyCounter = -1L;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Faculty(String name, String color) {
        this.name = name;
        this.color = color;
        facultyCounter++;
        id = facultyCounter;
    }
}
