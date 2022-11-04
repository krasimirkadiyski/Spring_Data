import entities.Employee;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeesFromDepartment_05 {
    private  static final String HQL_SELECT_EMPLOYEES_FROM_SELECTED_DEPARTMENT = "FROM Employee e where e.department.name = :dpName order by e.salary, e.id";
    public static void main(String[] args) {
        EntityManager entityManager = Utils.entityManager();

        Query query = entityManager.createQuery(HQL_SELECT_EMPLOYEES_FROM_SELECTED_DEPARTMENT, Employee.class);
        query.setParameter("dpName", "Research and Development");
        List<Employee> resultList = query.getResultList();
        for (Employee employee : resultList) {
            System.out.println(String.format("%s %s from Research and Development - $%.2f",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getSalary()));
        }


    }
}
