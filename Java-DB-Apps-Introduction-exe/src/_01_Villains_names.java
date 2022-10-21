import java.sql.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;
import java.util.Scanner;

public class _01_Villains_names {
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
                            name, COUNT(DISTINCT mv.minion_id) count
                        FROM
                            villains v
                                JOIN
                            minions_villains mv ON v.id = mv.villain_id
                        GROUP BY mv.villain_id
                        HAVING count > 15
                        ORDER BY count DESC
                        """);

        ResultSet result = stmt.executeQuery();
        if (!result.next()){
            return;
        }

        System.out.printf("%s %s", result.getString("name"), result.getInt("count"));

        //Connection connection = DriverManager
        //                .getConnection("jdbc:mysql://localhost:3306/soft_uni", props);
        //
        //        PreparedStatement stmt =
        //                connection.prepareStatement("SELECT * FROM employees WHERE salary > ?");
        //
        //        String salary = sc.nextLine();
        //        stmt.setDouble(1, Double.parseDouble(salary));
        //        ResultSet rs = stmt.executeQuery();
        //
        //        while(rs.next()){
        //            System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
        //        }
        //        connection.close();
    }
}
