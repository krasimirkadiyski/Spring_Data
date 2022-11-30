package softuni.exam.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.util.DOW;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Forecast extends BaseEntity{
    @Column(name = "day_of_week", nullable = false)
    @Enumerated(EnumType.STRING)
    private DOW dayOfWeek;
    @Column(name = "max_temperature", nullable = false)
    private Double maxTemperature;
    @Column(name = "min_temperature", nullable = false)
    private Double minTemperature;
    @Column(nullable = false)
    private LocalTime sunrise;
    @Column(nullable = false)
    private LocalTime sunset;
    @ManyToOne
    private City city;

}
