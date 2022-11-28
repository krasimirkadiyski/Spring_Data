package softuni.exam.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Offer extends BaseEntity{
    @Positive
    @Column(nullable = false)
    private BigDecimal price;
    @Column(name = "published_on",nullable = false)
    private LocalDate publishedOn;
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private Apartment apartment;
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private Agent agent;
}
