package softuni.exam.models.dto.xmlDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportOfferDto {
    @XmlElement
    @Positive
    private BigDecimal price;
    @XmlElement
    private AgentWrapperDto agent;
    @XmlElement
    private ApartmentWrapperDto apartment;
    @XmlElement
    public String publishedOn;
}
