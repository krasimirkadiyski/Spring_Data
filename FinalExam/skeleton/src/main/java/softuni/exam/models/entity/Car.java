package softuni.exam.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.util.CarType;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends BaseEntity{
    @Column(name = "car_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CarType carType;
    @Column(name = "car_make", nullable = false)
    private String carMake;
    @Column(name = "car_model", nullable = false)
    private String carModel;
    @Column(nullable = false)
    private Integer year;
    @Column(name = "plate_number", nullable = false, unique = true)
    private String plateNumber;
    @Column(nullable = false)
    private Integer kilometers;
    @Column(nullable = false)
    private Double engine;
}
