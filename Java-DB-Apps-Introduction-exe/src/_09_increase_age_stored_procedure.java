import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public record _09_increase_age_stored_procedure() {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);


        Properties props = new Properties();

        props.setProperty("user", "root");
        props.setProperty("password", "qwerty123456");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        int minionId = Integer.parseInt(scanner.nextLine());
        CallableStatement updateMinions = connection.prepareCall("{CALL usp_get_older(?)}");

        updateMinions.setInt(1,minionId);
        updateMinions.execute();

        connection.close();
    }
}
