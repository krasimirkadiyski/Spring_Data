package exam.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "town")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTownDto {
    @XmlElement
    @NotNull
    @Size(min = 2)
    private String name;
    @XmlElement
    @NotNull
    @Min(1)
    private int population;
    @XmlElement(name = "travel-guide")
    @NotNull
    @Size(min = 10)
    private String travelGuide;
}
