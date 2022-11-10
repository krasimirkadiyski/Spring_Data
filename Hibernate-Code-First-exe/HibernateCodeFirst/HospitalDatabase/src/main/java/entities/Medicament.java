package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @ManyToMany(mappedBy = "medications",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Patient> patients;
    public Medicament() {
        this.patients = new HashSet<>();
    }

    public Medicament(String name) {
        this();
        this.name = name;
    }

    public void addPatient(Patient patient){
        this.patients.add(patient);
    }

}
