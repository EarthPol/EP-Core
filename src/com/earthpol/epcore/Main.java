package com.earthpol.epcore;

import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main instance;
	public static Logger log = Bukkit.getLogger();
	private Connection connection;
	//public String host, database, username, password;
	//public int port;
	//public boolean ssl;
	
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
		
		/*
		log.info("= Loading config.yml");
		getConfig().options().copyDefaults();
		host = (String) getConfig().get("host");
		database = (String) getConfig().get("database");
		username = (String) getConfig().get("username");
		password = (String) getConfig().get("password");
		port = getConfig().getInt("port");
		ssl = getConfig().getBoolean("ssl");
		saveDefaultConfig();
		*/
		log.info("= Setting up options.yml");
		Config.setup();
		Config.getConfig();
		Config.saveConfig();
		//log.info("= Establishing MySQL Connection");
		//mysqlSetup();
		log.info("= Registering EventListener");
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		log.info("=========================");
		log.info("= Startup completed.");
		log.info("=========================");
	}
	
	/*public void mysqlSetup() {
		try {
			synchronized (this) {
				if(getConnection() != null && !getConnection().isClosed()) {
					return;
				}
				Class.forName("com.mysql.jdbc.Driver");
				setConnection(DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + 
				database, username, password));
				log.info("= MySQL Connection Established");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}*/
	
	public Connection getConnection(){
		return connection;
	}
	
	public void setConnection(Connection connection) {
		instance.connection = connection;
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
