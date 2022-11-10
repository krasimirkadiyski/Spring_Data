package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "diagnoses")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String comment;
    @ManyToOne()
    private Patient patient;
    public Diagnose() {
    }

    public Diagnose(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }
}
