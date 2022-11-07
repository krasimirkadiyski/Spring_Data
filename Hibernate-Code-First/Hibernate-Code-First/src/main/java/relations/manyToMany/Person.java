package relations.manyToMany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "person_ff",
    joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "favourite_food_id", referencedColumnName = "id"))
    private Set<FavouriteFood> favouriteFood;
    public Person() {
        this.favouriteFood = new HashSet<>();
    }

    public Person(String name) {
        this();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<FavouriteFood> getFavouriteFood() {
        return favouriteFood;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFavouriteFood(Set<FavouriteFood> favouriteFood) {
        this.favouriteFood = favouriteFood;
    }
    public void addFavouriteFood (FavouriteFood food){
        this.favouriteFood.add(food);
    }
}
