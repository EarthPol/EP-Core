package com.earthpol.epcore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;

public class SQLSetGet {
	public static Main plugin = Main.getPlugin(Main.class);
	
	public static boolean playerExists(UUID uuid) {
		try {
			PreparedStatement statement = plugin.getConnection()
					.prepareStatement("SELECT * FROM player_data WHERE UUID=?");
			statement.setString(1, uuid.toString());
			
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				plugin.log.info("[EP-Core] Player Data Found in MySQL");
				return true;
			} else {
				plugin.log.info("[EP-Core] Player Data Not Found in MySQL [!]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void createPlayer(final UUID uuid, Player player) {
		try {
			PreparedStatement statement = plugin.getConnection()
					.prepareStatement("SELECT * FROM player_data  WHERE UUID=?");
			statement.setString(1, uuid.toString());
			ResultSet results = statement.executeQuery();
			results.next();
			if(playerExists(uuid) != true) {
				PreparedStatement insert = plugin.getConnection()
						.prepareStatement("INSERT INTO player_data (UUID,NAME,LEVEL) VALUE (?,?,?)");
				insert.setString(1, uuid.toString());
				insert.setString(2, player.getName());
				insert.setInt(3, 0);
				insert.executeUpdate();
				plugin.log.info("[EP-Core] Player Data inserted into MySQL");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
