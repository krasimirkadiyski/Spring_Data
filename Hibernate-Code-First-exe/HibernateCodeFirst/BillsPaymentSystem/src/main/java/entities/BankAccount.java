package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "bank_acc")
public class BankAccount extends BillingDetails{
    private static final String type = "DEBIT";
    private String name;
    private String SWIFTCode;


    public BankAccount(String number, String owner, String name, String SWIFTCode) {
        super(type, number, owner);
        this.name = name;
        this.SWIFTCode = SWIFTCode;
    }


    public BankAccount() {

    }
}
