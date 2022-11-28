package com.example.football.models.dto.xml;

import com.example.football.util.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
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
@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPlayerDto {
    @Size(min = 3)
    @NotNull
    @XmlElement(name = "first-name")
    private String firstName;
    @Size(min = 3)
    @NotNull
    @XmlElement(name = "last-name")
    private String lastName;
    @Email
    @NotNull
    @XmlElement
    private String email;
    @XmlElement(name = "birth-date")
    private String birthdate;
    @XmlElement
    @NotNull
    private Position position;
    @XmlElement(name = "town")
    private ImportTownXmlDto town;
    @XmlElement(name = "team")
    private ImportTeamXmlDto team;
    @XmlElement(name = "stat")
    private ImportStatXmlDto stat;
}
