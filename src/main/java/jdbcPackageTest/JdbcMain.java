package jdbcPackageTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcMain {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/sundayfunday";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin";

    public static void main(String[] args) throws SQLException {

        //1. use driver manager to create connection object
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        //2.Using a connection object we can create a statement object.
        Statement statement = connection.createStatement();

        String selectAllPlayers = "SELECT * FROM players;";

        ResultSet resultAllPlayers = statement.executeQuery(selectAllPlayers);

        List<Player> players = new ArrayList<>();

        while ((resultAllPlayers.next())) {
            //nurodu stulpelio pavadinima
            String firstName = resultAllPlayers.getString("first_name");
            String lastName = resultAllPlayers.getString("last_name");
            String teamName = resultAllPlayers.getString("team");
            int wins = resultAllPlayers.getInt("wins");
            int loses = resultAllPlayers.getInt("loses");
            double height = resultAllPlayers.getDouble("height");
            double ppg = resultAllPlayers.getDouble("ppg");
            int id = resultAllPlayers.getInt("id");

            Player player = Player.builder()
                    .id(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .team(teamName)
                    .wins(wins)
                    .loses(loses)
                    .height(height)
                    .ppg(ppg)
                    .build();
            players.add(player);
        }

//        for (Player player : players) {
//            System.out.println(player);
//
//        }
        System.out.println("****");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM players");
        //ResultSet resultSet = statement.executeQuery("SELECT * FROM players WHERE team = 'Spurs'");
        while (resultSet.next()) {
            System.out.print(resultSet.getString("first_name"));
            System.out.print(" ");
            System.out.print(resultSet.getString("last_name"));
            System.out.println();
        }

        //insert into ....
//        String insertSql = "INSERT INTO players VALUES (21, 'Lebron', 'James', 'Heat', 2.11, 35.5, 56, 12)";
//        int effectedRows = statement.executeUpdate(insertSql);
//
//        if (effectedRows == 1) {
//            System.out.println("iterpta sekmingai");
//        } else {
//            System.out.println("neiterpta");
//        }
    }
}

