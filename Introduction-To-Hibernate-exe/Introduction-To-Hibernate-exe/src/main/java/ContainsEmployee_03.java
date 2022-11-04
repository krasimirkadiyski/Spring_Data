import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class ContainsEmployee_03 {
    //При проблем с HQL right click on Your Project ---> Framework support : check mark on "Hibernate"
    private static final String HQL_QUERY_FROM_EMPLOYEES_BY_NAME = "FROM Employee e WHERE e.firstName = :fn AND e.lastName = :ln";
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().trim().split(" ");
        String firstName = input[0];
        String lastName = input[1];
        EntityManager entityManager = Utils.entityManager();

        Query query = entityManager.createQuery(HQL_QUERY_FROM_EMPLOYEES_BY_NAME);
        query.setParameter("fn", firstName);
        query.setParameter("ln", lastName);
        List resultList = query.getResultList();

        if (resultList.isEmpty()){
            System.out.println("NO");
        }else {
            System.out.println("YES");
        }


    }
}
