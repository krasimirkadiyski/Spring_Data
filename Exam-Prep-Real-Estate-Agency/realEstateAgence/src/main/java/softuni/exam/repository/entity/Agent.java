package softuni.exam.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agent extends BaseEntity{
    @Column(nullable = false, name = "first_name", unique = true)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Fetch(FetchMode.JOIN)
    @ManyToOne
    private Town town;
    @Fetch(FetchMode.JOIN)
    @OneToMany(targetEntity = Offer.class, mappedBy = "agent")
    private Set<Offer> offers;

}
