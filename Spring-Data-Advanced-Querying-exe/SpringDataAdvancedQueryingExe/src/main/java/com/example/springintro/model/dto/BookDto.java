package com.example.springintro.model.dto;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.EditionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String title;
    private AgeRestriction ageRestriction;
    private EditionType editionType;
    private BigDecimal price;

    @Override
    public String toString() {
        return "BOOK {" +
                "title='" + title + '\'' +
                ", ageRestriction=" + ageRestriction +
                ", editionType=" + editionType +
                ", price=" + price +
                '}';
    }
}
