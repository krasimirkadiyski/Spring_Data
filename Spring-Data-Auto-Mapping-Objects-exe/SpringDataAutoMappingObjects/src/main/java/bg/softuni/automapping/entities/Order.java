package bg.softuni.automapping.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{
    @ManyToOne
    private User buyer;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> games;
}
