package com.earthpol.epcore;

import com.destroystokyo.paper.event.entity.PreCreatureSpawnEvent;
import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlockType;
import com.palmergames.bukkit.towny.object.WorldCoord;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {
    Towny towny;
    TownyUniverse tuniv;

    String prefix = ChatColor.GOLD + "[" + ChatColor.AQUA + "EPMC" + ChatColor.GOLD + "]: " + ChatColor.RESET + ChatColor.YELLOW;
    boolean mobSpawning;

    @EventHandler
    public void preMobSpawn(PreCreatureSpawnEvent event){
        EntityType ent = event.getType();

        if (ent == EntityType.RABBIT ||
                ent == EntityType.POLAR_BEAR ||
                ent == EntityType.DONKEY ||
                ent == EntityType.MULE ||
                ent == EntityType.BAT ||
                ent == EntityType.SQUID ||
                ent == EntityType.COD  ||
                ent == EntityType.ENDERMITE ||
                ent == EntityType.TROPICAL_FISH ||
                ent == EntityType.LLAMA ){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void playerLogin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        World world = player.getWorld();
        int onlinePlayers = onlinePlayerCount();

        if(onlinePlayers >= 80){
            if (mobSpawning == true){
                world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
                Bukkit.broadcastMessage(prefix + "Mob Spawning has been "+ ChatColor.RED + "Disabled");
                mobSpawning = false;
            }
        }

    }

    @EventHandler
    public void playerLogout(PlayerQuitEvent event){
        Player player = event.getPlayer();
        World world = player.getWorld();
        int onlinePlayers = onlinePlayerCount();

        if (onlinePlayers <= 80){
            if (mobSpawning == false){
                world.setGameRule(GameRule.DO_MOB_SPAWNING, true);
                Bukkit.broadcastMessage(prefix + "Mob Spawning has been "+ ChatColor.GREEN + "Enabled");
                mobSpawning = true;
            }
        }
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
                        whoHit.sendMessage(prefix + "You attempted to hit " + wasHit.getName() + ", but you are not in town.");
                        e.setDamage(0.0D);
                        e.setCancelled(true);
                    }
                } catch (NotRegisteredException error) {
                    whoHit.sendMessage(prefix + "You attempted to hit " + wasHit.getName() + ", but you are not in a town");
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

    public int onlinePlayerCount(){
        int playersOnline = (int) Main.instance.getServer().getOnlinePlayers().stream().count();
        return playersOnline;
    }
}
