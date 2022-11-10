package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "billing_details")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class BillingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(insertable = false,updatable = false)
    private String type;
    private String number;
    @ManyToOne
    private User owner;


    protected BillingDetails() {}

    protected BillingDetails(String type, String number, String owner) {
        this.type = type;
        this.number = number;
    }
}
