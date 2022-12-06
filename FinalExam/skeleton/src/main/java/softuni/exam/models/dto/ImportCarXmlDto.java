package softuni.exam.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.util.CarType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCarXmlDto {
    @NotNull
    @Size(min = 2 , max = 30)
    private String carMake;
    @NotNull
    @Size(min = 2 , max = 30)
    private String carModel;
    @NotNull
    @Min(1)
    private Integer year;
    @NotNull
    @Size(min = 2 , max = 30)
    private String plateNumber;
    @NotNull
    @Min(1)
    private Integer kilometers;
    @NotNull
    @DecimalMin("1")
    private Double engine;
    @NotNull
    private CarType carType;
}
