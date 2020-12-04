package com.earthpol.epcore;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	
	public static Main plugin = Main.getPlugin(Main.class);
	private static File file;
	private static FileConfiguration configFile;
	
	//Finds or generates the custom configuration file.
	public static void setup() {
		file = new File(Bukkit.getServer().getPluginManager().getPlugin("EP-Core").getDataFolder(), "options.yml");
		
		//If file does not exist, make one, duh.
		if(!file.exists()) {
			try {
				//Creates New File
				file.createNewFile();
			} catch (IOException e){
				//Error, let user know.
				plugin.log.info("Couldn't create a configuration file.");
			}
		}
		
		configFile = YamlConfiguration.loadConfiguration(file);
	}
	
	public static FileConfiguration getConfig() {
		configFile.addDefault("townless_pvp", "true");
		configFile.addDefault("nationless_pvp", "true");
		configFile.addDefault("enemy_pvp", "true");
		configFile.options().copyDefaults(true);
		return configFile;
	}
	
	public static void saveConfig() {
		try {
			configFile.save(file);
		} catch (IOException e) {
			//Error, let user know.
			plugin.log.info("Couldn't save the configuration file.");
		}
	}
	
	public static void reloadConfig() {
		configFile = YamlConfiguration.loadConfiguration(file);
	}
}
