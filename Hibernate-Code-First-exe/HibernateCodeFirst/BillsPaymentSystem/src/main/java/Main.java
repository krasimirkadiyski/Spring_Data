import entities.BankAccount;
import entities.BillingDetails;
import entities.CreditCard;
import entities.User;
import utils.EManager;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = EManager.entityManager();
        entityManager.getTransaction().begin();
        User user = new User("Peter","Petrov","petko@abv.bg","0000");
        BillingDetails creditCard1 = new CreditCard("55555","PETER","master",10,22);
        user.addBill(creditCard1);
        creditCard1.setOwner(user);
        entityManager.persist(user);
        entityManager.getTransaction().commit();

    }
}