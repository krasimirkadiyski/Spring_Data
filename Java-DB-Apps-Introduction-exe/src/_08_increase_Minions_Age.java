import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _08_increase_Minions_Age {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password","qwerty123456");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);
        List<Integer> minionsIds = Arrays.stream((scanner.nextLine().split(" "))).map(Integer::parseInt).toList();

        for (int i = 0; i < minionsIds.size(); i++) {
            int current = minionsIds.get(i);
            PreparedStatement updateMinionAge = connection.prepareStatement("""
                    update minions
                    set age = age + 1,
                    `name` = lower(`name`)
                    where id = ?;""");
            updateMinionAge.setInt(1,current);
            updateMinionAge.execute();
        }

        PreparedStatement getAllMinions = connection.prepareStatement("""
                select `name`, age from minions""");

        ResultSet rsMinionAge = getAllMinions.executeQuery();

        while(rsMinionAge.next()){
           String mName = rsMinionAge.getString("name");
           int mAge = rsMinionAge.getInt("age");
            System.out.printf("%s %d\n",mName,mAge);
        }
        connection.close();
    }
}
