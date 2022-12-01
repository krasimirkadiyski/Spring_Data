package exam.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportShopDto {
    @XmlElement
    @Size(min = 4)
    @NotNull
    private String address;
    @XmlElement(name = "employee-count")
    @Min(1)
    @Max(50)
    @NotNull
    private int employeeCount;
    @XmlElement
    @Min(20000)
    @NotNull
    private BigDecimal income;
    @XmlElement
    @Size(min = 4)
    @NotNull
    private String name;
    @XmlElement(name = "shop-area")
    @NotNull
    @Min(150)
    private Double shopArea;
    @XmlElement
    private TownDto town;
}
