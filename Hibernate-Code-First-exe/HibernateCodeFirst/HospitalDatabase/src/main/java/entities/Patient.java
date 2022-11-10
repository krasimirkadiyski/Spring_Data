package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter
@Setter
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String email;
    @Column(name = "date_of_birth",nullable = false)
    private Date dateOfBirth;
    private String picture;
    private String information;
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Visitation> visitations;
    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Diagnose> diagnoses;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Medicament> medications;

    //constructors
    public Patient() {
        this.visitations = new HashSet<>();
        this.diagnoses = new HashSet<>();
        this.medications = new HashSet<>();
    }

    public Patient(String firstName, String lastName, String address, String email, Date dateOfBirth, String picture, String information) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.picture = picture;
        this.information = information;
    }

    public void addVisitation(Visitation visitation){
        visitations.add(visitation);
    }

    public void addDiagnose(Diagnose diagnose){
        this.diagnoses.add(diagnose);
    }
    public void addMedicament(Medicament medicament){
        this.medications.add(medicament);
    }
}
