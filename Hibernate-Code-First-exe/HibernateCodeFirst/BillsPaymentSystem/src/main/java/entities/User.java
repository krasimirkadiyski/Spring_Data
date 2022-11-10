package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = BillingDetails.class, mappedBy = "owner")
    private Set<BillingDetails> billingDetailsSet;

    public User() {
        this.billingDetailsSet = new HashSet<>();
    }

    public User(String firstName, String lastName, String email, String password) {
       this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    public void addBill(BillingDetails billingDetails){
        billingDetailsSet.add(billingDetails);
    }
}
