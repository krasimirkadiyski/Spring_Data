package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Student {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
@Column(name = "first_name")
    private String firstName;
@Column(name = "last_name")
    private String lastName;
@Column(name = "phone_number")
    private String phoneNumber;
@Column(name = "average_grade")
    private double averageGrade;
    private double attendance;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Course> courses;

    public Student() {
        this.courses = new HashSet<>();
    }

    public Student(String firstName, String lastName, String phoneNumber, double averageGrade, double attendance) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.averageGrade = averageGrade;
        this.attendance = attendance;
    }
    public void addCourse(Course course){
        this.courses.add(course);
    }
}
