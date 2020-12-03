package com.earthpol.epcore;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main instance;
	public static Logger log = Bukkit.getLogger();
	
	@Override
	public void onEnable() {
		instance = this;
		log.info("======= EPMC CORE =======");
		log.info("= EarthPol Core Plugin");
		log.info("= Version: " + this.getDescription().getVersion());
		log.info("= Authors: " + this.getDescription().getAuthors());
		log.info("= Website: " + this.getDescription().getWebsite());
		log.info("= Support: https://discord.gg/DvtZzztAfF");
		log.info("=========================");
		
		//Setup Configuration File.
		getConfig().options().copyDefaults();
		saveDefaultConfig();
		
		//Setup Custom Configuration File and Load it.
		Config.setup();
		
		//Get the configuration file.
		Config.getConfig();
		
		//Save the configuration File.
		Config.saveConfig();
	}
	
	@Override
	public void onDisable() {
		log.info("======= EPMC CORE =======");
		log.info("= EarthPol Core Plugin");
		log.info("= Disabling plugin...");
		log.info("= Thank you for using EP-Core");
		log.info("= Support: https://discord.gg/DvtZzztAfF");
		log.info("=========================");
	}
}
