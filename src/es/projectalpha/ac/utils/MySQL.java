package es.projectalpha.ac.utils;

import com.mysql.jdbc.CommunicationsException;
import es.projectalpha.ac.AVC;
import es.projectalpha.ac.api.AVCUser;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;

/**
 * MySQL
 *
 * @author Huskehhh base original, Cadox8 update, Cadiducho first update
 */
public class MySQL {

    @Getter protected Connection connection;

    private final String user, database, password, port, hostname;

    public MySQL(String hostname, String port, String database, String username, String password) {
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = username;
        this.password = password;
    }

    public boolean checkConnection() throws SQLException {
        return connection != null && !connection.isClosed();
    }

    public boolean closeConnection() throws SQLException {
        if (connection == null) {
            return false;
        }
        connection.close();
        return true;
    }

    public Connection openConnection() throws SQLException, ClassNotFoundException {
        if (checkConnection()) {
            return connection;
        }

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://"
                + this.hostname + ":" + this.port + "/" + this.database + "?autoReconnect=true&&allowMultiQueries=true", this.user, this.password);
        return connection;
    }

    // -----------------
    public void setupTable(Player p) {
        AVC.getInstance().getServer().getScheduler().runTaskAsynchronously(AVC.getInstance(), () -> {
            try {
                PreparedStatement statement = openConnection().prepareStatement("SELECT `id` FROM `users` WHERE `uuid` = ?");
                statement.setString(1, p.getUniqueId().toString());
                ResultSet rs = statement.executeQuery();

                if (!rs.next()) {
                    PreparedStatement insertData = openConnection().prepareStatement("INSERT INTO `users` (`uuid`, `name`, `money`) VALUES (?, ?, ?)");
                    insertData.setString(1, p.getUniqueId().toString());
                    insertData.setString(2, p.getName());
                    insertData.setDouble(3, 0);
                    insertData.executeUpdate();


                    // Testing...
                    PreparedStatement insetAll = openConnection().prepareStatement("INSERT INTO `lemonade` (`uuid`) VALUES (?); INSERT INTO `bank` (`uuid`) VALUES (?);" +
                            "INSERT INTO `car` (`uuid`) VALUES (?); INSERT INTO `donut` (`uuid`) VALUES (?); INSERT INTO `hockey` (`uuid`) VALUES (?);" +
                            "INSERT INTO `movie` (`uuid`) VALUES (?); INSERT INTO `news` (`uuid`) VALUES (?); INSERT INTO `oil` (`uuid`) VALUES (?);" +
                            "INSERT INTO `pizza` (`uuid`) VALUES (?); INSERT INTO `shrimp` (`uuid`) VALUES (?)");
                    insetAll.setString(1, p.getUniqueId().toString());
                    insetAll.executeUpdate();

/*                    PreparedStatement insertLemonade = openConnection().prepareStatement("INSERT INTO `lemonade` (`uuid`) VALUES (?)");
                    insertLemonade.setString(1, p.getUniqueId().toString());
                    insertLemonade.executeUpdate();

                    PreparedStatement insertBank = openConnection().prepareStatement("INSERT INTO `bank` (`uuid`) VALUES (?)");
                    insertBank.setString(1, p.getUniqueId().toString());
                    insertBank.executeUpdate();

                    PreparedStatement insertCar = openConnection().prepareStatement("INSERT INTO `car` (`uuid`) VALUES (?)");
                    insertCar.setString(1, p.getUniqueId().toString());
                    insertCar.executeUpdate();

                    PreparedStatement insertDonut = openConnection().prepareStatement("INSERT INTO `donut` (`uuid`) VALUES (?)");
                    insertDonut.setString(1, p.getUniqueId().toString());
                    insertDonut.executeUpdate();

                    PreparedStatement insertHockey = openConnection().prepareStatement("INSERT INTO `hockey` (`uuid`) VALUES (?)");
                    insertHockey.setString(1, p.getUniqueId().toString());
                    insertHockey.executeUpdate();

                    PreparedStatement insertMovie = openConnection().prepareStatement("INSERT INTO `movie` (`uuid`) VALUES (?)");
                    insertMovie.setString(1, p.getUniqueId().toString());
                    insertMovie.executeUpdate();

                    PreparedStatement insertNews = openConnection().prepareStatement("INSERT INTO `news` (`uuid`) VALUES (?)");
                    insertNews.setString(1, p.getUniqueId().toString());
                    insertNews.executeUpdate();

                    PreparedStatement insertOil = openConnection().prepareStatement("INSERT INTO `oil` (`uuid`) VALUES (?)");
                    insertOil.setString(1, p.getUniqueId().toString());
                    insertOil.executeUpdate();

                    PreparedStatement insertPizza = openConnection().prepareStatement("INSERT INTO `pizza` (`uuid`) VALUES (?)");
                    insertPizza.setString(1, p.getUniqueId().toString());
                    insertPizza.executeUpdate();

                    PreparedStatement insertShrimp = openConnection().prepareStatement("INSERT INTO `shrimp` (`uuid`) VALUES (?)");
                    insertShrimp.setString(1, p.getUniqueId().toString());
                    insertShrimp.executeUpdate();*/
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void saveUser(AVCUser u) {
        AVC.getInstance().getServer().getScheduler().runTaskAsynchronously(AVC.getInstance(), () -> {
            AVCUser.UserData data = u.getUserData();
            try {
                PreparedStatement statementData = openConnection().prepareStatement("UPDATE `users` SET `money`=? WHERE `uuid`=?");
                statementData.setDouble(1, data.getMoney());
                statementData.setString(2, u.getUuid().toString());
                statementData.executeUpdate();


            } catch (Exception ex) {
                System.err.println("Error while saving data of " + u.getName());
                ex.printStackTrace();
            }
        });
    }

    public AVCUser.UserData loadUserData(UUID id) {
        AVCUser.UserData data = new AVCUser.UserData();
        try {
            PreparedStatement statementDatos = openConnection().prepareStatement("SELECT `money` FROM `users` WHERE `uuid` = ?");
            statementDatos.setString(1, id.toString());
            ResultSet rsDatos = statementDatos.executeQuery();

            if (rsDatos.next()) {
                data.setMoney(rsDatos.getDouble("money"));
            }
        } catch (CommunicationsException ex) {
            try {
                closeConnection();
                openConnection();
                return loadUserData(id);
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        } catch (Exception ex) {
            System.err.println("Error while loading data from " + id);
            ex.printStackTrace();
        }
        return data;
    }
}
