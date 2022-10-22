import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _03_add_minions {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();

        props.setProperty("user", "root");
        props.setProperty("password", "qwerty123456");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);


        List<String> inputMinion = Arrays.stream(scanner.nextLine().split(" ")).skip(1).toList();
        List<String> inputVillain = Arrays.stream(scanner.nextLine().split(" ")).skip(1).toList();
        String mName = inputMinion.get(0);
        int mAge = Integer.parseInt(inputMinion.get(1));
        String mTown = inputMinion.get(2);
        String vName = inputVillain.get(0);
        PreparedStatement checkTownExists = connection
                .prepareStatement("""
                        SELECT\s
                            *
                        FROM
                            towns
                        WHERE
                            `name` = ?""");

        PreparedStatement addTown = connection
                .prepareStatement("""
                        insert into towns (`name`)
                        values (?)""");
        checkTownExists.setString(1,mTown);

        if (!checkTownExists.executeQuery().next()){
            addTown.setString(1,mTown);
            addTown.execute();
            System.out.printf("Town %s was added to the database.\n",mTown);
        }

        PreparedStatement checkVillainExists = connection.prepareStatement("""
                select * from villains\s
                where `name` = ?""");
        checkVillainExists.setString(1,vName);

        PreparedStatement addVillain = connection.prepareStatement("""
                insert into villains (`name`, evilness_factor)
                values (?,?)""");
        if (!checkVillainExists.executeQuery().next()){
            addVillain.setString(1,vName);
            addVillain.setString(2, "evil");
            addVillain.execute();
            System.out.printf("Villain %s was added to the database.\n", vName);
        }

        PreparedStatement getTownId = connection.prepareStatement("""
                select id from towns
                where `name` = ?""");
        getTownId.setString(1,mTown);
        ResultSet townSet = getTownId.executeQuery();
        int townId = !townSet.next() ? -1 : Integer.parseInt(townSet.getString("id"));


        PreparedStatement getVillainId = connection.prepareStatement("""
                select id from villains
                where `name` = ?""");
        getVillainId.setString(1,vName);
        ResultSet villainSet = getVillainId.executeQuery();
        int villainId = !villainSet.next() ? -1 : Integer.parseInt(villainSet.getString("id"));

        PreparedStatement addMinion = connection.prepareStatement("""
                insert into minions (`name`, age, town_id)
                values (?,?,?)""");

        addMinion.setString(1,mName);
        addMinion.setInt(2,mAge);
        addMinion.setInt(3,townId);
        addMinion.execute();

        PreparedStatement getMinionId = connection.prepareStatement("""
                select id from minions
                where `name` = ?""");
        getMinionId.setString(1,mName);
        ResultSet minionSet = getMinionId.executeQuery();
        int minionId = !minionSet.next() ? -1 : Integer.parseInt(minionSet.getString("id"));

        PreparedStatement insertIntoMV = connection.prepareStatement("""
                insert into minions_villains (minion_id, villain_id)
                values (?,?)""");
        insertIntoMV.setInt(1,minionId);
        insertIntoMV.setInt(2,villainId);
        System.out.printf("Successfully added %s to be minion of %s",mName,vName);
        connection.close();
    }
}
