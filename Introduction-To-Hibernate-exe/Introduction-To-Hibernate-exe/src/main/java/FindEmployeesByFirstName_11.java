import entities.Employee;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class FindEmployeesByFirstName_11 {
    public static void main(String[] args) {
        EntityManager entityManager = Utils.entityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String startWith = scanner.nextLine();

        entityManager.
                createQuery("FROM Employee e WHERE e.firstName LIKE :startWith", Employee.class).
                setParameter("startWith", startWith + "%")
                .getResultList().
                forEach(e -> System.out.printf("%s %s - %s - (%s)%n",e.getFirstName(),e.getLastName(),e.getJobTitle(),e.getSalary()));


    }
}
