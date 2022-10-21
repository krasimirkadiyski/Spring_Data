import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _06_remove_villains {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password","qwerty123456");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        int villainId = Integer.parseInt(scanner.nextLine());

        PreparedStatement getCountOfMinions = connection.prepareStatement("""
                select count(*) count, v.name as villainName from minions m\s
                join minions_villains mv
                on mv.minion_id = m.id
                join villains v
                on v.id = mv.villain_id
                where villain_id = ?
                group by villain_id;""");
        getCountOfMinions.setInt(1,villainId);

        ResultSet rsMinions_villains
                 = getCountOfMinions.executeQuery();

        if (!rsMinions_villains
                .next()){
            System.out.println("No such villain was found");
            return;
        }

        int minionsCount =  rsMinions_villains
                .getInt("count");
        String villainName = rsMinions_villains.getString("villainName");


        PreparedStatement deleteVillainFromMV = connection.prepareStatement("""
                delete from minions_villains
                where villain_id = ?;
               """);
        deleteVillainFromMV.setInt(1,villainId);
        deleteVillainFromMV.execute();


        PreparedStatement deleteVillainFromVillains = connection.prepareStatement("""
                delete from villains
                where id = ?;
               """);
        deleteVillainFromVillains.setInt(1,villainId);
        deleteVillainFromVillains.execute();
        System.out.printf("%s was deleted\n" +
                "%d minions released\n",villainName,minionsCount);

    }
}
