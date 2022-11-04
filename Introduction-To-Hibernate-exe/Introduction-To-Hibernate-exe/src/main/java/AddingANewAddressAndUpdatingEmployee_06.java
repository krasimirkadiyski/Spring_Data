

import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Scanner;

public class AddingANewAddressAndUpdatingEmployee_06 {
    private static final String HQL_UPDATE_EMPLOYEE_ADDRESS_Vitoshka_15 = "UPDATE Employee e set e.address = :ad WHERE e.lastName = :ln";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager entityManager = Utils.entityManager();

        entityManager.getTransaction().begin();
        Address address = new Address();
        entityManager.persist(address);
        address.setText("Vitoshka 15");
        Query query = entityManager.createQuery(HQL_UPDATE_EMPLOYEE_ADDRESS_Vitoshka_15);
        query.setParameter("ad", address);
        String lastName = scanner.nextLine().trim();
        query.setParameter("ln", lastName);
        query.executeUpdate();
        entityManager.getTransaction().commit();

    }
}
