package bg.softuni.automapping.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class Game extends BaseEntity{
    private String title;
    private BigDecimal price;
    private double size;
    private String trailer;
    private String image;
    private String description;
    private LocalDate releaseDate;
    @ManyToMany
    private Set<User> user;

    public Game(String title, BigDecimal price, double size, String trailer, String image, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.image = image;
        this.description = description;
        this.releaseDate = releaseDate;
    }
}
