import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _05_change_towns_names {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);


        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password","qwerty123456");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String country = scanner.nextLine();

        PreparedStatement updateToUpper = connection.prepareStatement("""
                UPDATE towns\s
                SET\s
                    `name` = UPPER(`name`)
                WHERE
                    country = ? AND id > 0;""");

        updateToUpper.setString(1,country);

        int affectedRows = updateToUpper.executeUpdate();
        System.out.println(affectedRows);

        PreparedStatement getAffectedTowns = connection.prepareStatement("""
                select `name` from towns
                where country = ?""");
        getAffectedTowns.setString(1,country);

        ResultSet affectedTowns = getAffectedTowns.executeQuery();
        List<String> townsList = new ArrayList<>();
        while (affectedTowns.next()){
            townsList.add(affectedTowns.getString("name"));
        }
        String result = String.join(", ", townsList);
        System.out.println("[" + result + "]");
        connection.close();
    }
}
