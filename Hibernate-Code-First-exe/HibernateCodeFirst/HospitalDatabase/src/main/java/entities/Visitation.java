package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "visitations")
public class Visitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private String comment;
    @ManyToOne
    private Patient patient;

    public Visitation() {
    }

    public Visitation(Date date, String comment) {
        this.date = date;
        this.comment = comment;
    }
}
