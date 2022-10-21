import java.sql.*;
import java.util.*;

public class _07_print_all_minions_names {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password","qwerty123456");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);


        PreparedStatement getAllMinions = connection.prepareStatement("""
                select `name` from minions""");

        ResultSet rsAllMinions = getAllMinions.executeQuery();

        ArrayDeque<String> minionsNames = new ArrayDeque<>();

        while (rsAllMinions.next()){
            minionsNames.offer(rsAllMinions.getString("name"));
        }

        while (minionsNames.size() >= 2){
            System.out.println(minionsNames.pop());
            System.out.println(minionsNames.pollLast());
        }
        if (!minionsNames.isEmpty()){
            System.out.println(minionsNames.poll());
        }

        connection.close();
    }
}
