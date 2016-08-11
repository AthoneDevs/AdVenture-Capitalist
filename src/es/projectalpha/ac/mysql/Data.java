package es.projectalpha.ac.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import es.projectalpha.ac.AVC;
import es.projectalpha.ac.game.Currency;
import es.projectalpha.ac.utils.Messages;

public class Data {

	private AVC plugin;
	private Connection conn;
	private String tableName = "avc_data";
	private Currency c = new Currency();

	public Data(AVC Main){
		this.plugin = Main;
	}

	public boolean hasAccount(UUID p){
		this.conn = this.plugin.getMySQL().getConnection();
		try {

			String sql = "SELECT `player_uuid` FROM `" + this.tableName + "` WHERE `player_uuid` = ?";
			PreparedStatement preparedUpdateStatement = this.conn.prepareStatement(sql);
			preparedUpdateStatement.setString(1, p.toString());

			ResultSet result = preparedUpdateStatement.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.RED + "Error: " + e.getMessage());
		}
		return false;
	}

	public boolean createAccount(UUID uuid, Player p){
		this.conn = this.plugin.getMySQL().getConnection();
		try {

			String sql = "INSERT INTO `" + this.tableName + "`(`player_uuid`, `player_name`, `money`) " + "VALUES(?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = this.conn.prepareStatement(sql);

			preparedStatement.setString(1, uuid.toString());
			preparedStatement.setString(2, p.getName());
			preparedStatement.setString(3, c.getSMoney(p));

			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.RED + "Error: " + e.getMessage());
		}
		return false;
	}

	public boolean setMoney(UUID uuid, Player p, String money){
		if (!hasAccount(uuid)) {
			createAccount(uuid, p);
		}
		this.conn = this.plugin.getMySQL().getConnection();
		try {

			String updateSqlExp = "UPDATE `" + this.tableName + "` " + "SET `player_name` = ?" + ", `money` = ?" + " WHERE `player_uuid` = ?";
			PreparedStatement preparedUpdateStatement = this.conn.prepareStatement(updateSqlExp);
			preparedUpdateStatement.setString(1, p.getName());
			preparedUpdateStatement.setString(2, money);
			preparedUpdateStatement.setString(3, uuid.toString());

			preparedUpdateStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.RED + "Error: " + e.getMessage());
		}
		return false;
	}

	public String getMoney(UUID uuid){
		if (!hasAccount(uuid)) {
			createAccount(uuid, null);
		}
		this.conn = this.plugin.getMySQL().getConnection();
		try {

			String sql = "SELECT `money` FROM `" + this.tableName + "` WHERE `player_uuid` = ?";

			PreparedStatement preparedUpdateStatement = this.conn.prepareStatement(sql);
			preparedUpdateStatement.setString(1, uuid.toString());
			ResultSet result = preparedUpdateStatement.executeQuery();
			if (result.next()) {
				return result.getString("money");
			}
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage(Messages.prefix + ChatColor.RED + "Error: " + e.getMessage());
		}
		return null;
	}
}
