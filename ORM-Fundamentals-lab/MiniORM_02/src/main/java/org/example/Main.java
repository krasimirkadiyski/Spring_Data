package org.example;

import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException {
        MyConnector.createConnection("root","qwerty123456", "soft_uni");
        EntityManager<User> user = new EntityManager<>(MyConnector.getConnection());
        User first = new User ("Second", 2, LocalDate.now());

        first.setAge(25);
        first.setId(4);



        user.persist(first);

    }
}