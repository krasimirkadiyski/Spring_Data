package entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    private int credits;
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
    @ManyToOne( fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Teacher teacher;
    public Course() {
        this.students = new HashSet<>();
    }

    public Course(String name, String description, LocalDate startDate, LocalDate endDate, int credits) {
        this();
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.credits = credits;

    }
    public void addStudent(Student student){
        this.students.add(student);
    }
}
