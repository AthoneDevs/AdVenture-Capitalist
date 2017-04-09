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
                + this.hostname + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.user, this.password);
        return connection;
    }

    // -----------------
    public void setupTable(Player p) {
        AVC.getInstance().getServer().getScheduler().runTaskAsynchronously(AVC.getInstance(), () -> {
            try {
                PreparedStatement statement = openConnection().prepareStatement("SELECT `id` FROM `avc_data` WHERE `uuid` = ?");
                statement.setString(1, p.getUniqueId().toString());
                ResultSet rs = statement.executeQuery();
                if (!rs.next()) { //No hay filas encontradas, insertar nuevos datos
                    PreparedStatement inserDatos = openConnection().prepareStatement("INSERT INTO `avc_data` (`uuid`, `name`, `money`) VALUES (?, ?, ?)");
                    inserDatos.setString(1, p.getUniqueId().toString());
                    inserDatos.setString(2, p.getName());
                    inserDatos.setDouble(3, 0);
                    inserDatos.executeUpdate();
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
                PreparedStatement statementDatos = openConnection().prepareStatement("UPDATE `avc_data` SET `money`=? WHERE `uuid`=?");
                statementDatos.setDouble(1, u.getUserData().getMoney());
                statementDatos.setString(2, u.getUuid().toString());
                statementDatos.executeUpdate();
            } catch (Exception ex) {
                System.err.println("Error while saving data of " + u.getName());
                ex.printStackTrace();
            }
        });
    }

    public AVCUser.UserData loadUserData(UUID id) {
        AVCUser.UserData data = new AVCUser.UserData();
        try {
            PreparedStatement statementDatos = openConnection().prepareStatement("SELECT `money` FROM `avc_data` WHERE `uuid` = ?");
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
