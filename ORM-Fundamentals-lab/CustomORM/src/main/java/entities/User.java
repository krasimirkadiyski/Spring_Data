package entities;

import orm.annotataions.Column;
import orm.annotataions.Entity;
import orm.annotataions.Id;

import java.time.LocalDate;
@Entity(name = "users")
public class User {
    public User() {
    }

    @Id
    private long id;
    @Column(name = "user_name")
    private String username;
    @Column(name = "age")

    private int age;
    @Column(name = "registration_date")
    private LocalDate registration;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getRegistration() {
        return registration;
    }

    public User(String username, int age, LocalDate registration) {
        this.username = username;
        this.age = age;
        this.registration = registration;
    }
}
