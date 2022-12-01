package exam.model.entities;

import exam.util.WarrantyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Laptop extends BaseEntity {
    @Column(name = "max_address", nullable = false, unique = true)
    private String macAddress;
    @Column(name = "cpu_speed", nullable = false)
    private Double cpuSpeed;
    @Column(nullable = false)
    private Integer ram;
    @Column(nullable = false)
    private Integer storage;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(name = "warranty_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private WarrantyType warrantyType;
    @ManyToOne
    private Shop shop;


}
