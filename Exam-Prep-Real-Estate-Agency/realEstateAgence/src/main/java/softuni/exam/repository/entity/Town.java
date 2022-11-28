package softuni.exam.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Town extends BaseEntity {
    @Column(name = "town_name", unique = true, nullable = false)
    private String townName;
    @Column(nullable = false)
    private Long population;
    @OneToMany(targetEntity = Agent.class, mappedBy = "town")
    private Set<Agent> agents;
    @OneToMany(targetEntity = Apartment.class, mappedBy = "town")
    private Set<Apartment> apartment;

}
