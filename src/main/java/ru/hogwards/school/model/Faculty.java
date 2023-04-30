package ru.hogwards.school.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Faculty {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String color;
    @OneToMany(mappedBy = "faculty")
    private Collection<Student> students;
    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    public Faculty() {
    }

    public Faculty(String name, String color) {
        this.name = name;
        this.color = color;
        this.id = id;
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty)) return false;

        Faculty faculty = (Faculty) o;

        if (getId() != null ? !getId().equals(faculty.getId()) : faculty.getId() != null) return false;
        if (getName() != null ? !getName().equals(faculty.getName()) : faculty.getName() != null) return false;
        return getColor() != null ? getColor().equals(faculty.getColor()) : faculty.getColor() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        return result;
    }
}