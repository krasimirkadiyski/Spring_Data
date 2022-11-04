import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class IncreaseSalaries_10 {
    private static final String HQL_UPDATE_EMP_SALARY = "UPDATE Employee e SET e.salary = e.salary * 1.12  WHERE e.department.id in (1, 2, 14,3)";
    private static final String HQL_UPDATED_EMPLOYEES_IN_DEPARTMENT = "FROM Employee e WHERE e.department.id in (1, 2, 14,3)";
    public static void main(String[] args) {
        EntityManager entityManager = Utils.entityManager();

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(HQL_UPDATE_EMP_SALARY);
        int countOfUpdates = query.executeUpdate();

        if (countOfUpdates != 0) {
            entityManager.getTransaction().commit();

            entityManager.createQuery(HQL_UPDATED_EMPLOYEES_IN_DEPARTMENT, Employee.class)
                    .getResultList()
                    .forEach(e -> System.out.printf("%s %s (%s)%n", e.getFirstName(), e.getLastName(), e.getSalary()));
        } else {
            entityManager.getTransaction().rollback();
        }

        entityManager.close();
    }
}
