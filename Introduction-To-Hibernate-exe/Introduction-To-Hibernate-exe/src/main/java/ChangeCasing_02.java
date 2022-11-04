import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ChangeCasing_02 {
    public static void main(String[] args) {

        EntityManager em = Utils.entityManager();
        em.getTransaction().begin();

        String HqlQuery = "UPDATE Town t set t.name = upper(t.name) WHERE length(t.name) <= 5";

        Query query = em.createQuery(HqlQuery);
        query.executeUpdate();
        em.getTransaction().commit();

    }
}
