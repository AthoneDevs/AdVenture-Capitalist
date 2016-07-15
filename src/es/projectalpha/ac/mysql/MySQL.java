package es.projectalpha.ac.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.files.Files;
import es.projectalpha.ac.utils.Messages;

public class MySQL {

	public Connection conn = null;
	private String tableName = "avc_data";
	private String dbHost;
	private String dbPort;
	private String database;
	private String dbUser;
	private String dbPassword;

	public MySQL(AVC Main){
		setupDatabase();
	}

	public boolean setupDatabase(){
		try {
			Class.forName("com.mysql.jdbc.Driver");

			this.dbHost = Files.cfg.getString("MySQL.host");
			this.dbPort = Files.cfg.getString("MySQL.port");
			this.database = Files.cfg.getString("MySQL.db");
			this.dbUser = Files.cfg.getString("MySQL.user");
			this.dbPassword = Files.cfg.getString("MySQL.pass");

			String passFix = this.dbPassword.replaceAll("%", "%25");
			String passFix2 = passFix.replaceAll("\\+", "%2B");

			this.conn = DriverManager.getConnection("jdbc:mysql://" + this.dbHost + ":" + this.dbPort + "/" + this.database + "?" + "user=" + this.dbUser + "&" + "password=" + passFix2);
		} catch (SQLException | ClassNotFoundException e1) {
			Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.RED + "Can't connect to MySQL " + e1.getMessage());
		}

		try {
			Statement query = this.conn.createStatement();

			String accounts = "CREATE TABLE IF NOT EXISTS `" + this.tableName + "` (id int(10) AUTO_INCREMENT, player_uuid varchar(50) NOT NULL UNIQUE, player_name varchar(50) NOT NULL, money varchar(20000) NOT NULL,  PRIMARY KEY(id));";
			query.executeUpdate(accounts);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "Connected to MySQL");
		return true;
	}

	public Connection getConnection(){
		checkConnection();
		return this.conn;
	}

	public boolean closeConnection(){
		try {
			this.conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkConnection(){
		try {
			if (this.conn == null) {
				Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.YELLOW + "Connection failed, reconnecting");
				if (reConnect()) {
					return true;
				}
				return false;
			}
			if (!this.conn.isValid(3)) {
				Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.YELLOW + "Connection finished, reconnecting");
				if (reConnect()) {
					return true;
				}
				return false;
			}
			if (this.conn.isClosed()) {
				Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.YELLOW + "Connection closed, reconnecting");
				if (reConnect()) {
					return true;
				}
				return false;
			}
			return true;
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.RED + "Could not reconnect to Database!");
		}
		return true;
	}

	public boolean reConnect(){
		try {
			this.dbHost = Files.cfg.getString("MySQL.host");
			this.dbPort = Files.cfg.getString("MySQL.port");
			this.database = Files.cfg.getString("MySQL.db");
			this.dbUser = Files.cfg.getString("MySQL.user");
			this.dbPassword = Files.cfg.getString("MySQL.pass");

			String passFix = this.dbPassword.replaceAll("%", "%25");
			String passFix2 = passFix.replaceAll("\\+", "%2B");

			long start = 0L;
			long end = 0L;

			start = System.currentTimeMillis();
			Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.YELLOW + "Trying to connect to MySQL");
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection("jdbc:mysql://" + this.dbHost + ":" + this.dbPort + "/" + this.database + "?" + "user=" + this.dbUser + "&" + "password=" + passFix2);
			end = System.currentTimeMillis();
			Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.GREEN + "Connection to MySQL server established in " + (end - start) + "ms!");
			return true;
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.RED + "Can't connect to MySQL " + e.getMessage());
		}
		return false;
	}
}
