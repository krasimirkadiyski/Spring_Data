package bg.softuni.springdataautomappingobjects.entities_exe3;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private BigDecimal salary;
    @Column(name = "is_employee_on_holiday")
    private Boolean isEmployeeOnHoliday;
    private LocalDate birthday;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @ManyToOne
    private Employee manager;

    public Employee(String firstName, String lastName, BigDecimal salary, Boolean isEmployeeOnHoliday, Address address, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.isEmployeeOnHoliday = isEmployeeOnHoliday;
        this.address = address;
        this.birthday = birthday;
    }



}