package relations.manyToMany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FavouriteFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "favouriteFood",targetEntity = Person.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Person> personSet;
    public FavouriteFood() {
        this.personSet = new HashSet<>();
    }

    public FavouriteFood(String name) {
        this();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Person> getPersonSet() {
        return personSet;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonSet(Set<Person> personSet) {
        this.personSet = personSet;
    }

    public  void addPerson(Person person){
        this.personSet.add(person);
    }
}
