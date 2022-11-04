import entities.Employee;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;


public class GetEmployeeWithProject_08 {
        private static final String HQL_EMPLOYEE_INFO_BY_ID = "FROM Employee e WHERE e.id = :id";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager entityManager = Utils.entityManager();
        TypedQuery<Employee> query = entityManager.createQuery(HQL_EMPLOYEE_INFO_BY_ID, Employee.class);
        int searchedId = scanner.nextInt();
        query.setParameter("id", searchedId);
        Employee em = query.getSingleResult();
        System.out.printf("%s %s - %s",em.getFirstName(),em.getLastName(),em.getJobTitle());
        List<String> projects = em.getProjects().stream().map(p -> p.getName()).sorted().toList();
        for (String project : projects) {
            System.out.println(project);
        }


    }
}
