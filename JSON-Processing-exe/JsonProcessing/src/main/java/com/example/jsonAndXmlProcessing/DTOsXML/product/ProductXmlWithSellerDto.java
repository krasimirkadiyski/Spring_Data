package com.example.jsonAndXmlProcessing.DTOsXML.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductXmlWithSellerDto {
    @XmlAttribute()
    private String name;
    @XmlAttribute()
    private BigDecimal price;
    @XmlAttribute(name = "seller")
    private String sellerFullName;
}
