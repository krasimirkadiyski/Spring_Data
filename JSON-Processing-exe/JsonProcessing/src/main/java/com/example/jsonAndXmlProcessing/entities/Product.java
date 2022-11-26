package com.example.jsonAndXmlProcessing.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Min(value = 3)
    @Column
    private String name;
    @Column
    private BigDecimal price;
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private User buyer;
    @ManyToOne(fetch = FetchType.EAGER)
    private User seller;
    @ManyToMany()
    @Fetch(FetchMode.JOIN)
    private Set<Category> categories;
    public String getSellerFullName(){
        return String.format("%s %s",this.seller.getFirstName(),
                this.seller.getLastName());
    }



}
