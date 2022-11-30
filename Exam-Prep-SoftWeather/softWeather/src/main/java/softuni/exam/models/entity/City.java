package softuni.exam.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities")
public class City extends BaseEntity{
    @Column(name = "city_name", nullable = false, unique = true)
    private String cityName;
    @Column(nullable = false, columnDefinition = "Text")
    private String description;
    @Column(nullable = false)
    private Long population;
    @ManyToOne
    private Country country;
}
