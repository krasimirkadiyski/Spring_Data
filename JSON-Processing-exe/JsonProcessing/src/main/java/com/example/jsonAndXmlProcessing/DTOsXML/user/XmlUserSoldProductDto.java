package com.example.jsonAndXmlProcessing.DTOsXML.user;

import com.example.jsonAndXmlProcessing.DTOsXML.product.SoldProductWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlUserSoldProductDto {
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlElement(name = "sold-product")
    private SoldProductWrapper sellingProducts;



}
