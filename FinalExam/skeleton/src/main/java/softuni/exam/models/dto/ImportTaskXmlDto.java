package softuni.exam.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTaskXmlDto {
    @XmlElement
    @NotNull
    private String date;
    @XmlElement
    @DecimalMin("0.01")
    @NotNull
    private Double price;
    @XmlElement
    private ImportCarXmlNested car;
    @XmlElement
    private ImportMechanicXmlNested mechanic;
    @XmlElement
    private ImportPartXmlNested part;
}
