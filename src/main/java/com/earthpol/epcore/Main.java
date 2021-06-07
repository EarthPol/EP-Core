package com.earthpol.epcore;

import com.earthpol.epcore.chat.ChatHandler;
import com.earthpol.epcore.commands.*;
import com.earthpol.epcore.deathmessage.DeathMessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public static Main instance;
    public static Logger log = Bukkit.getLogger();
    public static String prefix = "§6[§bEPMC§6]: §r§e";
    String discord = "https://discord.gg/teKxw4rAhv";

    public static ChatHandler chatHandler;
    public static DeathMessageHandler deathMessageHandler;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        log.info("§e======= §bEPMC CORE §e=======");
        log.info("§e= §bEarthPol Core Plugin");
        log.info("§e= §bVersion: §3" + this.getDescription().getVersion());
        log.info("§e= §bAuthors: §3" + this.getDescription().getAuthors());
        log.info("§e= §bWebsite: §3" + this.getDescription().getWebsite());
        log.info("§e= §bSupport: §3" + discord );
        log.info("§e=========================");
        log.info("§e= §bRegistering EventListener");
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        log.info("§e= §aRegistered EventListener");
        log.info("§e=========================");
        log.info("§e= §bInitializing ChatHandler");
        chatHandler = new ChatHandler();
        log.info("§e= §aInitialized ChatHandler");
        log.info("§e=========================");
        log.info("§e= §bInitializing DeathMessageHandler");
        deathMessageHandler = new DeathMessageHandler();
        log.info("§e= §bInitialized DeathMessageHandler");
        log.info("§e=========================");
        log.info("§e= §bRegistering Commands");
        Objects.requireNonNull(getCommand("map")).setExecutor(new Map());
        Objects.requireNonNull(getCommand("help")).setExecutor(new Help());
        Objects.requireNonNull(getCommand("rules")).setExecutor(new Rules());
        Objects.requireNonNull(getCommand("store")).setExecutor(new Store());
        Objects.requireNonNull(getCommand("support")).setExecutor(new Support());
        Objects.requireNonNull(getCommand("mapcolor")).setExecutor(new MapColor());
        Objects.requireNonNull(getCommand("ping")).setExecutor(new Ping());
        log.info("§e= §aRegistered Commands");
        log.info("§e=========================");
        log.info("§e= §aStartup completed.");
        log.info("§e=========================");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        log.info("§e======= §bEPMC CORE §e=======");
        log.info("§e= §bEarthPol Core Plugin");
        log.info("§e= §bDisabling plugin...");
        log.info("§e= §bThank you for using EP-Core");
        log.info("§e= §bSupport: §3" + discord );
        log.info("§e=========================");
    }
}
