package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    @Column(name = "salary_per_hour")
    private double salaryPerHour;
    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Course> courses;

    public Teacher() {
        this.courses = new HashSet<>();
    }

    public Teacher(String firstName, String lastName, String phoneNumber, String email, double salaryPerHour) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.salaryPerHour = salaryPerHour;
    }
    public void addCourse(Course course){
        this.courses.add(course);
    }
}
