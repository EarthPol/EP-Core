package com.earthpol.epcore;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.destroystokyo.paper.event.entity.PreCreatureSpawnEvent;
import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlockType;
import com.palmergames.bukkit.towny.object.WorldCoord;
import com.palmergames.bukkit.towny.war.flagwar.events.CellAttackCanceledEvent;
import com.palmergames.bukkit.towny.war.flagwar.events.CellAttackEvent;
import com.palmergames.bukkit.towny.war.flagwar.events.CellDefendedEvent;
import com.palmergames.bukkit.towny.war.flagwar.events.CellWonEvent;
import java.util.Arrays;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


public class EventListener implements Listener{
	  Towny towny;
	  TownyUniverse tuniv;
	  public Boolean flagWar = Boolean.valueOf(false);
	  Boolean townlesspvp = (Boolean) Config.getConfig().get("townlesspvp");
	  
	  @EventHandler
	  public void onCommandUse(PlayerCommandPreprocessEvent e) {
	    Player player = e.getPlayer();
	    List<String> commands = Arrays.asList(new String[] { 
	          "pl", "about", "version", "ver", "plugins", "minecraft:pl", "minecraft:plugins", "minecraft:about", "minecraft:version", "minecaft:ver", 
	          "auctionhouse:ah", "auctionhouse:auction", "auctionhouse:auctionhouse", "ah", "auction", "auctionhouse" });
	    int cSize = commands.size();
	    commands.forEach(all -> {
	          if (e.getMessage().toLowerCase().equalsIgnoreCase("/" + all.toLowerCase()))
	            if (all.toLowerCase() == "auctionhouse:ah" || all.toLowerCase() == "auctionhouse:auction" || all.toLowerCase() == "auctionhouse:auctionhouse" || all.toLowerCase() == "ah" || all.toLowerCase() == "auction" || all.toLowerCase() == "auctionhouse") {
	              try {
	                TownBlockType plotType = WorldCoord.parseWorldCoord(player.getLocation()).getTownBlock().getType();
	                if (plotType != TownBlockType.COMMERCIAL) {
	                  player.sendMessage(ChatColor.GOLD + "You cannot use the Auction House outside of a shop plot.");
	                  e.setCancelled(true);
	                } 
	              } catch (NotRegisteredException e1) {
	                e.setCancelled(true);
	                player.sendMessage(ChatColor.GOLD + "You cannot use the Auction House outside of a shop plot.");
	              } 
	            } else {
	              e.setCancelled(true);
	            }  
	        });
	  }

	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
		Player p = e.getPlayer();
		//SQLSetGet.createPlayer(p.getUniqueId(), p);
		if(p.isOp()){
			e.setJoinMessage("");
		} else {
			e.setJoinMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " joined the game.");
		}
    }
	
	@SuppressWarnings("null")
	@EventHandler
	public void preMobSpawn(PreCreatureSpawnEvent e) {
		//Get the current Type of the Entity
		EntityType entity = e.getType();
		EntityType[] illegals = null;
		illegals[0] = EntityType.RABBIT;
		illegals[1] = EntityType.POLAR_BEAR;
		illegals[2] = EntityType.DONKEY;
		illegals[3] = EntityType.MULE;
		illegals[4] = EntityType.BAT;
		illegals[5] = EntityType.SQUID;
		if (illegals.toString().contains(entity.toString())) {
			e.setCancelled(true);
		}
	}
	
	 @EventHandler
	  public void onCellAttackEvent(CellAttackEvent e) {
	    this.flagWar = Boolean.valueOf(true);
	  }
	  
	  @EventHandler
	  public void onCellDefendedEvent(CellDefendedEvent event) {
	    this.flagWar = Boolean.valueOf(false);
	  }
	  
	  @EventHandler
	  public void onCellWonEvent(CellWonEvent e) {
	    this.flagWar = Boolean.valueOf(false);
	  }
	  
	  @EventHandler
	  public void onCellAttackCanceledEvent(CellAttackCanceledEvent e) {
	    this.flagWar = Boolean.valueOf(false);
	  }
	  
	  @EventHandler
	  public void onPlayerDamage(EntityDamageByEntityEvent e) throws TownyException {
	    if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
	      Player wasHit = (Player)e.getEntity();
	      Player whoHit = (Player)e.getDamager();
	      String whoHitName = whoHit.getName();
	      String wasHitName = wasHit.getName();
	      Resident attacker = TownyUniverse.getInstance().getDataSource().getResident(whoHitName);
	      Resident defender = TownyUniverse.getInstance().getDataSource().getResident(wasHitName);
	      boolean attackerHasTown = attacker.hasTown();
	      boolean defenderHasTown = defender.hasTown();
		if (!attackerHasTown && defenderHasTown) {
			try {
			Town town = WorldCoord.parseWorldCoord(wasHit.getLocation()).getTownBlock().getTown();
			TownBlockType plotType = WorldCoord.parseWorldCoord(wasHit.getLocation()).getTownBlock().getType();
			if (!town.hasOutlaw(whoHit.getName()) && plotType != TownBlockType.ARENA) {
				whoHit.sendMessage(ChatColor.GOLD + "You attempted to hit " + wasHit.getName() + ", but you are not in town.");
				e.setDamage(0.0D);
				e.setCancelled(true);
				} 
			} catch (NotRegisteredException error) {
				whoHit.sendMessage(ChatColor.GOLD + "You attempted to hit " + wasHit.getName() + ", but you are not in a town");
			    e.setDamage(0.0D);
			    e.setCancelled(true);
			    } 
			} else if (attackerHasTown && !defenderHasTown) {
				try {
					Town town = WorldCoord.parseWorldCoord(wasHit.getLocation()).getTownBlock().getTown();
					TownBlockType plotType = WorldCoord.parseWorldCoord(wasHit.getLocation()).getTownBlock().getType();
					if (!town.hasOutlaw(wasHit.getName()) && plotType != TownBlockType.ARENA) {
						whoHit.sendMessage(ChatColor.GOLD + "You attempted to hit " + wasHit.getName() + ", but they are not an outlaw of your town.");
						e.setDamage(0.0D);
						e.setCancelled(true);
					} 
				} catch (NotRegisteredException error) {
					whoHit.sendMessage(ChatColor.GOLD + "You attempted to hit " + wasHit.getName() + ", but they are not in town.");
					e.setDamage(0.0D);
					e.setCancelled(true);
			      } 
			 	} 
	      
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

	
	@EventHandler
	public void  onPlayerDeath(PlayerDeathEvent e) {
		Player p = (Player) e.getEntity();
		
	}

}
