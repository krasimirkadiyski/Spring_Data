package org.example;

import org.example.Utils.EManager;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = EManager.entityManager();
        entityManager.getTransaction().begin();
        WizardDeposit wz = new WizardDeposit();
        wz.setAge(5);
        wz.set_deposit_expired(false);
        wz.setDeposit_amount(526.25);
        wz.setDeposit_charge(12.20);
        wz.setDeposit_group("dont know");
        wz.setDeposit_interest(2.3);
        wz.setDeposit_start_date(LocalDate.now());
        wz.setFirst_name("Goshka");
        wz.setLast_name("Goshkova");
        entityManager.persist(wz);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}