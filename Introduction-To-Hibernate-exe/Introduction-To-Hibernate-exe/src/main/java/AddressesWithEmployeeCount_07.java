
import entities.Address;

import javax.persistence.EntityManager;
import java.util.List;


public class AddressesWithEmployeeCount_07 {
private static final String HQL_ALL_ADDRESSES_BY_NUMBER_OF_EMPLOYEES = "FROM Address a ORDER BY a.employees.size DESC";
    public static void main(String[] args) {
        EntityManager entityManager = Utils.entityManager();
        entityManager.getTransaction().begin();

        List<Address> addressList = entityManager.createQuery(HQL_ALL_ADDRESSES_BY_NUMBER_OF_EMPLOYEES, Address.class).setMaxResults(10).getResultList();

        for (Address address : addressList) {
            System.out.println(address);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

