package com.earthpol.epcore;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.md_5.bungee.api.ChatColor;

public class EventListener implements Listener{
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
		Player p = e.getPlayer();
		SQLSetGet.createPlayer(p.getUniqueId(), p);
		if(p.isOp()){
			e.setJoinMessage("");
		} else {
			e.setJoinMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " joined the game.");
		}
    }
	
	@EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
		Player p = e.getPlayer();
		if(p.isOp()) {
			e.setQuitMessage("");
		} else {
			e.setQuitMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " left the game.");
		}

    }

}
