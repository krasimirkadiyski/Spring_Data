import entities.Customer;
import entities.Product;
import entities.Sale;
import entities.StoreLocation;
import utils.EManager;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = EManager.entityManager();
        entityManager.getTransaction().begin();
        Product product = new Product("Banana",2, BigDecimal.valueOf(2.6));
        StoreLocation storeLocation = new StoreLocation("Sofia St 8");
        Customer customer = new Customer("Ivan", "vankata@abv.bg","1258-8215-2253");
        Sale sale = new Sale(product,customer,storeLocation, LocalDate.now());
        entityManager.persist(sale);
        entityManager.getTransaction().commit();
    }
}