package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "credit_card")
public class CreditCard extends BillingDetails{
    private static final String type = "CREDIT";
    private String cardType;
    private int expirationMonth;
    private int expirationYear;

    public CreditCard(){};

    public CreditCard(String number, String owner, String cardType, int expirationMonth, int expirationYear) {
        super(type, number, owner);
        this.cardType = cardType;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }
}
