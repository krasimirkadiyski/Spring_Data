package bg.softuni.automapping.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "games")
public class Game extends BaseEntity{
    private String title;
    private String trailer;
    private String image;
    private double size;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;
    @ManyToMany(targetEntity = Order.class, mappedBy = "games")
    private List<Order> orders;


}
