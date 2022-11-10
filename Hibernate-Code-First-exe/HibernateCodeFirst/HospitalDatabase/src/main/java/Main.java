import entities.Diagnose;
import entities.Medicament;
import entities.Patient;
import entities.Visitation;
import utils.EManager;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = EManager.entityManager();
        entityManager.getTransaction().begin();

        entityManager.getTransaction().commit();




    }
}