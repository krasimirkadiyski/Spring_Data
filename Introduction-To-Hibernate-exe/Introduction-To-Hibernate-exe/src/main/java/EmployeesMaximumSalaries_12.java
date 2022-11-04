import javax.persistence.EntityManager;

public class EmployeesMaximumSalaries_12 {
    public static void main(String[] args) {
        EntityManager entityManager = Utils.entityManager();

        entityManager.
                createQuery("SELECT e.department.name, MAX(e.salary) " +
                        "FROM Employee e " +
                        "GROUP BY e.department.name " +
                        "HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000", Object[].class).
                getResultList().
                forEach(o -> System.out.println(o[0] + " " +o[1]));
    }
}
