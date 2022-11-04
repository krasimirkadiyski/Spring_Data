import entities.Employee;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeesWithSalaryOver50000_04 {
    private static final String HQL_QUERY_SELECT_FROM_EMPLOYEES_SALARY_OVER_50000 = 
            "FROM Employee e where e.salary > 50000";
    public static void main(String[] args) {
        EntityManager entityManager = Utils.entityManager();
        TypedQuery<Employee> query = entityManager.createQuery(HQL_QUERY_SELECT_FROM_EMPLOYEES_SALARY_OVER_50000, Employee.class);
        List<Employee> resultList = query.getResultList();
        for (Employee employee : resultList) {
            System.out.println(employee.getFirstName());
        }


    }
}
