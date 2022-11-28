package com.example.football.models.dto.xml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportStatDto {
    @XmlElement
    @NotNull
    @DecimalMin("0.1")
    private Double passing;
    @XmlElement
    @NotNull
    @DecimalMin("0.1")
    private Double shooting;
    @XmlElement
    @NotNull
    @DecimalMin("0.1")
    @Positive
    private Double endurance;
}
