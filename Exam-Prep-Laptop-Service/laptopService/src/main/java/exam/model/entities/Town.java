package exam.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "towns")
public class Town extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Long population;
    @Column(name = "travel_guide", nullable = false, columnDefinition = "TEXT")
    private String travelGuide;

}
