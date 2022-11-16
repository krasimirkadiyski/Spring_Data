package bg.softuni.automapping.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String email;
    private String password;
    private String fullName;
    @OneToMany
    private Set<Game> games;
    private Boolean administrator;

}
