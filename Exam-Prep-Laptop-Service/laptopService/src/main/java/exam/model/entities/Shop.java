package exam.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shops")
public class Shop extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private BigDecimal income;
    @Column(nullable = false)
    private String address;
    @Column(name = "employee_count", nullable = false)
    private Integer employeeCount;
    @Column(name = "shop_area", nullable = false)
    private Integer shopArea;
    @ManyToOne
    private Town town;


}
