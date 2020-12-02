package com.earthpol.epcore;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main instance;
	public static Logger log = Bukkit.getLogger();
	public static PluginDescriptionFile pluginFile;
	String version = pluginFile.getVersion();
	List<String> authors = pluginFile.getAuthors();
	String website = pluginFile.getWebsite();
	
	@Override
	public void onEnable() {
		instance = this;
		log.info("======= EPMC CORE =======");
		log.info("= EarthPol Core Plugin");
		log.info("= Version: " + version);
		log.info("= Authors: " + authors);
		log.info("= Website: " + website);
		log.info("= Support: https://discord.gg/DvtZzztAfF");
		log.info("=========================");
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
