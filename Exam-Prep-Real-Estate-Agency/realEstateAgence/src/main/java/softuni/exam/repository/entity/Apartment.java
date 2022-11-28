package softuni.exam.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.util.ApartmentType;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Apartment extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApartmentType apartmentType;
    @Column(nullable = false)
    private Double area;
    @ManyToOne
    private Town town;
    @OneToMany(targetEntity = Offer.class, mappedBy = "apartment")
    private Set<Offer> offers;
}
