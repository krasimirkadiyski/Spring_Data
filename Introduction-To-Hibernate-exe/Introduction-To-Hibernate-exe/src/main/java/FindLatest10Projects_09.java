import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Comparator;
import java.util.List;

public class FindLatest10Projects_09 {
    private static final String HQL_SELECT_PROJ_ORD_BY_DATE = "FROM Project p ORDER BY p.startDate DESC ";
    public static void main(String[] args) {

        EntityManager entityManager = Utils.entityManager();

        TypedQuery<Project> query = entityManager.createQuery(HQL_SELECT_PROJ_ORD_BY_DATE, Project.class);
        query.setMaxResults(10)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf("""
                        Project name: %s
                         	Project Description: %s
                         	Project Start Date: %s
                         	Project End Date: %s
                        """,p.getName(),p.getDescription(),p.getStartDate(),p.getEndDate()));

    }
}
