package bg.softuni.automapping.entities;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static bg.softuni.automapping.constants.Constants.EMAIL_REGEX;
import static bg.softuni.automapping.constants.Constants.PASS_REGEX;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Builder(builderClassName = "Builder", builderMethodName = "build")
public class User extends BaseEntity {
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String fullName;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Game> games;
    private Boolean administrator = false;
    private Boolean loggedIn = false;


    public void purchaseGame(Game game){
        games.add(game);
    }
}
