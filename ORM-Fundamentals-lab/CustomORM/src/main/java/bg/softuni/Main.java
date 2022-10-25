package bg.softuni;

import entities.User;
import orm.Connector;
import orm.EntityManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException {
        Connector.createConnection("root", "qwerty123456", "soft_uni");
        Connection connection = Connector.getConnection();
        EntityManager<User> userManager = new EntityManager<>(connection);

//        User user = new User("First",2, LocalDate.now());
//        userManager.persist(user);
        User first = userManager.findFirst(User.class);
        System.out.println(first.getUsername());
    }
}