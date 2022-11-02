import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class Utils {
    public static EntityManager  entityManager (){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        return  factory.createEntityManager();
    };
}
