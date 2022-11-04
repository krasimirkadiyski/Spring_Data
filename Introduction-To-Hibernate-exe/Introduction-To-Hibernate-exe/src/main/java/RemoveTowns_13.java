

import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns_13 {
    private static final String HQL_ADDRESSES_IN_GIVEN_TOWN = "FROM Address a WHERE a.town.name = :town";
    private static final String HQL_TOWN_BY_GIVEN_NAME = "SELECT t FROM Town t WHERE t.name = :town";
    public static void main(String[] args) {
        EntityManager entityManager = Utils.entityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String town = scanner.nextLine();

        List<Address> addresses = entityManager.
                createQuery(HQL_ADDRESSES_IN_GIVEN_TOWN, Address.class).
                setParameter("town", town).
                getResultList();

        int deleteAddresses = addresses.size();

        if(deleteAddresses == 0){
            System.out.println("No such town");
            entityManager.close();
            return;
        }


        addresses.forEach(currentAd -> {
            currentAd.getEmployees().forEach(e -> e.setAddress(null));
            entityManager.remove(currentAd);
        });

        Town townFromDB = entityManager.
                createQuery(HQL_TOWN_BY_GIVEN_NAME, Town.class).
                setParameter("town", town).
                getSingleResult();

        entityManager.remove(townFromDB);

        String address = deleteAddresses == 1 ? "address" : "addresses";
        System.out.printf("%d %s in %s deleted %n", deleteAddresses, address, town);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
