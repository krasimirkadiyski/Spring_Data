package Utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class Em {
    public static javax.persistence.EntityManager entityManager (){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        return  factory.createEntityManager();
    }
}
