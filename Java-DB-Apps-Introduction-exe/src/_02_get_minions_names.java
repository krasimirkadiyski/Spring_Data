import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _02_get_minions_names {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "qwerty123456");

        Connection collection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement stmt =
                collection.prepareStatement("""
                        SELECT\s
                            v.name as villain, m.name as minion, m.age as age
                        FROM
                            villains v
                                JOIN
                            minions_villains mv ON v.id = mv.villain_id
                                JOIN
                            minions m ON m.id = mv.minion_id
                        WHERE
                            v.id = ?""");
        int searchedId = Integer.parseInt(scanner.nextLine());
        stmt.setInt(1, searchedId);

        ResultSet result = stmt.executeQuery();
        int index = 1;
        if (!result.next()) {
            System.out.printf("No villain with ID %d exists in the database.",searchedId);
        } else {

            System.out.printf("\nVillain: %s\n",result.getString("villain"));

            do {
                System.out.printf("%d. %s %d\n",index++, result.getString("minion"), result.getInt("age"));
            }
            while (result.next());
        }

    }
}
