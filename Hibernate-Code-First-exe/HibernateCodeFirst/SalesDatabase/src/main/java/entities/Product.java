package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double quantity;
    private BigDecimal price;
    @OneToMany(mappedBy = "product")
    private Set<Sale> sales;


    public Product() {
        this.sales = new HashSet<>();
    }

    public Product(String name, double quantity, BigDecimal price) {
        this();
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
