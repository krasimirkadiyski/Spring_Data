package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Product product;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private StoreLocation storeLocation;
    private LocalDate date;

    public Sale() {
    }

    public Sale(Product product, Customer customer, StoreLocation storeLocation, LocalDate date) {
        this();
        this.product = product;
        this.customer = customer;
        this.storeLocation = storeLocation;
        this.date = date;
    }
}
