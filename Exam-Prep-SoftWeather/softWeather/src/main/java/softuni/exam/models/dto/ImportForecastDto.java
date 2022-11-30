package softuni.exam.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.util.DOW;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportForecastDto {
    @XmlElement(name = "day_of_week")
    @NotNull
    private DOW dayOfWeek;
    @XmlElement(name = "max_temperature")
    @NotNull
    @DecimalMin("-20")
    @DecimalMax("60")
    private Double maxTemperature;
    @XmlElement(name = "min_temperature")
    @NotNull
    @DecimalMin("-50")
    @DecimalMax("40")
    private Double minTemperature;
    @XmlElement
    @NotNull
    private String sunrise;
    @XmlElement
    @NotNull
    private String sunset;
    @XmlElement
    @NotNull
    private long city;
}
