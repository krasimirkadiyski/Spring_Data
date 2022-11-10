package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "store_location")
public class StoreLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "location_name")
    private String locationName;
    @OneToMany(mappedBy = "storeLocation")
    private Set<Sale> sales;
    public void addSale(Sale sale){
        this.sales.add(sale);
    }

    public StoreLocation() {
        this.sales = new HashSet<>();
    }

    public StoreLocation(String locationName) {
        this.sales = new HashSet<>();
        this.locationName = locationName;
    }
}
